package com.training.springboot.transaction.service.impl;

import com.training.springboot.transaction.dto.AddressDto;
import com.training.springboot.transaction.entity.Address;
import com.training.springboot.transaction.repository.AddressRepository;
import com.training.springboot.transaction.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        //TODO: save city to address relationship
        if (!ObjectUtils.isEmpty(addressDto.getId())) {
            //logic for update data
            AddressDto oldAddressDto = this.getAddress(addressDto.getId());
            copyPropertiesForPatch(oldAddressDto, addressDto);
            addressDto = oldAddressDto;
        }
        //TODO: call city repo by City code in addressDto before save address.
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        Address savedAddress = addressRepository.save(address);
        addressDto.setId(savedAddress.getId());
        return addressDto;
    }

    private void copyPropertiesForPatch(AddressDto oldAddressDto, AddressDto addressDto) {
        //jika value dari frontend tidak null/empty, maka update value lama nya
        if (!ObjectUtils.isEmpty(addressDto.getAddressLine1())) {
            oldAddressDto.setAddressLine1(addressDto.getAddressLine1());
        }
        if (!ObjectUtils.isEmpty(addressDto.getAddressLine2())) {
            oldAddressDto.setAddressLine2(addressDto.getAddressLine2());
        }
        if (!ObjectUtils.isEmpty(addressDto.getNamaPenerima())) {
            oldAddressDto.setNamaPenerima(addressDto.getNamaPenerima());
        }
        if (!ObjectUtils.isEmpty(addressDto.getPrimaryAddress())) {
            oldAddressDto.setPrimaryAddress(addressDto.getPrimaryAddress());
        }
        if (!ObjectUtils.isEmpty(addressDto.getKodePos())) {
            oldAddressDto.setKodePos(addressDto.getKodePos());
        }
    }

    @Override
    public List<AddressDto> getAllAddress() {
        List<AddressDto> addressDtoList = new ArrayList<>();
        List<Address> addressList = addressRepository.findAll();
        for (Address eachAddress :
                addressList) {
            AddressDto addressDto = new AddressDto();
            BeanUtils.copyProperties(eachAddress, addressDto);
            addressDtoList.add(addressDto);
        }
        return addressDtoList;
    }

    @Override
    public AddressDto getAddress(Long id) {
        Address addressFromDb = addressRepository.getById(id);
        AddressDto addressDtoResponse = new AddressDto();
        BeanUtils.copyProperties(addressFromDb, addressDtoResponse);
        return addressDtoResponse;
    }

    @Override
    public void deleteAddress(Long id) {
        Address addressFromDb = addressRepository.getById(id);
        addressRepository.delete(addressFromDb);
    }

    @Override
    public List<AddressDto> getAllAddressPagination(int page, int contentPerPage) {
        Page<Address> addressPage = addressRepository.findAll(PageRequest.of(page, contentPerPage));
        List<Address> addressList = addressPage.getContent();
        List<AddressDto> resultList = new ArrayList<>();
        for (Address each : addressList) {
            AddressDto addressDto = new AddressDto();
            BeanUtils.copyProperties(each, addressDto);
            resultList.add(addressDto);
        }
        return resultList;
    }
}
