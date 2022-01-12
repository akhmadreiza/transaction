package com.training.springboot.transaction.controller;

import com.training.springboot.transaction.dto.AddressDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String printHello() {
        AddressDto addressDto = AddressDto.builder().addressLine1("Jl Mawar").addressLine2("No 1").build();
        System.out.println(addressDto.toString());
        return "Hello World";
    }
}
