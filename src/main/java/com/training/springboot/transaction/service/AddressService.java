package com.training.springboot.transaction.service;

import com.training.springboot.transaction.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto saveAddress(AddressDto addressDto);

    List<AddressDto> getAllAddress();

    AddressDto getAddress(Long id);

    void deleteAddress(Long id);

    List<AddressDto> getAllAddressPagination(int page, int contentPerPage, String searchKey);

    AddressDto getPrimaryAddress();
}
