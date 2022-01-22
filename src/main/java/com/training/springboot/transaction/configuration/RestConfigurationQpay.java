package com.training.springboot.transaction.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfigurationQpay {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(getClientHttpRequestFactory());
    }

    private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        //Connect timeout -> timeout when making an initial connection
        clientHttpRequestFactory.setConnectTimeout(10000); //millisecond
        //Read timeout -> timeout when on waiting to read the data
        clientHttpRequestFactory.setReadTimeout(10000); //millisecond
        return clientHttpRequestFactory;
    }
}
