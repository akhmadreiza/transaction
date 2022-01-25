package com.training.springboot.transaction.service.impl;

import com.training.springboot.transaction.constants.Bank;
import com.training.springboot.transaction.dto.PaymentCreationDto;
import com.training.springboot.transaction.dto.PaymentDto;
import com.training.springboot.transaction.dto.qpay.QpayRequestDto;
import com.training.springboot.transaction.dto.qpay.QpayResponseDto;
import com.training.springboot.transaction.service.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentGatewayServiceQpay implements PaymentGatewayService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${qpay.api.url}")
    private String qPayUrl;

    @Value("${qpay.api.key}")
    private String qPayApiKey;

    @Value("${qpay.api.secret}")
    private String qPayApiSecret;

    @Override
    public PaymentDto executePayment(PaymentCreationDto paymentCreationDto) {
        //call qpay API
        QpayRequestDto qpayRequestDto = QpayRequestDto.builder()
                .apiKey(qPayApiKey)
                .apiSecret(qPayApiSecret)
                .amount(paymentCreationDto.getAmount())
                .bank(Bank.BCA)
                .build();
        QpayResponseDto qpayResponseDto = restTemplate.postForObject(qPayUrl + "/create-va", qpayRequestDto, QpayResponseDto.class);
        PaymentDto paymentDtoResponse = new PaymentDto();
        paymentDtoResponse.setVirtualAccount(qpayResponseDto.getAccountVA());
        paymentDtoResponse.setTransactionId(qpayResponseDto.getTransactionId());
        return paymentDtoResponse;
    }
}
