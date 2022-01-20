package com.training.springboot.transaction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/public/hello")
    public String printHello() {
        return "Hello World";
    }
}
