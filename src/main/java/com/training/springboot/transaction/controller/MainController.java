package com.training.springboot.transaction.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@RequestMapping("/public")
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String printHello() throws InterruptedException {
        return "Hello World";
    }

    @GetMapping("/test-slow-api")
    public String testCallSlowApi() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/transaction-service/public/hello-slow", String.class);
            return response.getBody();
        } catch (RestClientException e) {
            log.error("ERROR!", e);
        }
        return "TIMEOUT";
    }

    @GetMapping("/hello-slow")
    public String printHelloSlow() throws InterruptedException {
        int sleepSecond = 100;
        Thread.sleep(sleepSecond * 1000);
        return "Hello World";
    }
}
