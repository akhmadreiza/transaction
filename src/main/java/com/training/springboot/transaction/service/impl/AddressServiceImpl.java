package com.training.springboot.transaction.service.impl;

import com.training.springboot.transaction.dto.AddressDto;
import com.training.springboot.transaction.dto.CityDto;
import com.training.springboot.transaction.entity.Address;
import com.training.springboot.transaction.entity.City;
import com.training.springboot.transaction.exception.DataNotFoundException;
import com.training.springboot.transaction.repository.AddressRepository;
import com.training.springboot.transaction.repository.CityRepository;
import com.training.springboot.transaction.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

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
        Optional<City> city = cityRepository.findById(addressDto.getCity().getCode());
        if (city.isPresent()) {
            BeanUtils.copyProperties(addressDto, address);
            address.setCity(city.get());
            Address savedAddress = addressRepository.save(address);
            addressDto.setId(savedAddress.getId());
            return addressDto;
        } else {
            throw new DataNotFoundException("City not found in database");
        }
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
        //city
        if (!ObjectUtils.isEmpty(addressDto.getCity())) {
            if (!ObjectUtils.isEmpty(addressDto.getCity().getCode())) {
                String newCityCode = addressDto.getCity().getCode();
                Optional<City> optionalCity = cityRepository.findById(newCityCode);
                if (optionalCity.isPresent()) {
                    CityDto oldCity = oldAddressDto.getCity();
                    oldCity.setCode(addressDto.getCity().getCode());
                    oldAddressDto.setCity(oldCity);
                } else {
                    throw new DataNotFoundException("City not found in database");
                }
            }
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
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return constructAddressDto(optionalAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address addressFromDb = addressRepository.getById(id);
        addressRepository.delete(addressFromDb);
    }

    @Override
    public List<AddressDto> getAllAddressPagination(int page, int contentPerPage, String searchKey) {
        Page<Address> addressPage;
        if (ObjectUtils.isEmpty(searchKey)) {
            addressPage = addressRepository.findAll(PageRequest.of(page, contentPerPage));
        } else {
            addressPage = addressRepository.searchAddress(PageRequest.of(page, contentPerPage), searchKey);
        }
        List<Address> addressList = addressPage.getContent();
        return constructAddressDto(addressList);
    }

    @Override
    public AddressDto getPrimaryAddress() {
        Address address = addressRepository.findByPrimaryAddressIs(true);
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address, addressDto);
        return addressDto;
    }

    private AddressDto constructAddressDto(Optional<Address> optionalAddress) {
        if (optionalAddress.isPresent()) {
            AddressDto addressDtoResponse = new AddressDto();
            BeanUtils.copyProperties(optionalAddress.get(), addressDtoResponse);
            CityDto cityDto = new CityDto();
            BeanUtils.copyProperties(optionalAddress.get().getCity(), cityDto);
            addressDtoResponse.setCity(cityDto);
            return addressDtoResponse;
        } else {
            throw new DataNotFoundException("Address not found in database");
        }
    }

    private List<AddressDto> constructAddressDto(List<Address> addresses) {
        List<AddressDto> resultList = new ArrayList<>();
        for (Address each : addresses) {
            AddressDto addressDto = new AddressDto();
            BeanUtils.copyProperties(each, addressDto);
            CityDto cityDto = new CityDto();
            BeanUtils.copyProperties(each.getCity(), cityDto);
            addressDto.setCity(cityDto);
            resultList.add(addressDto);
        }
        return resultList;
    }
}
