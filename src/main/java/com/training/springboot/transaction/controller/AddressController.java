package com.training.springboot.transaction.controller;

import com.training.springboot.transaction.dto.AddressDto;
import com.training.springboot.transaction.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public AddressDto getAddressData(@PathVariable Long id) {
        return addressService.getAddress(id);
    }

    @GetMapping(produces = "application/json")
    public List<AddressDto> getAllAddress(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int contentPerPage) {
        //first page is 0, not 1.
        return addressService.getAllAddressPagination(page, contentPerPage);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json")
    public AddressDto saveAddress(@Valid @RequestBody AddressDto addressDto) {
        return addressService.saveAddress(addressDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(value = "/{id}", produces = "application/json")
    public void updateAddress(@Valid @PathVariable @NotEmpty Long id, @RequestBody AddressDto addressDto) {
        addressDto.setId(id);
        addressService.saveAddress(addressDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
