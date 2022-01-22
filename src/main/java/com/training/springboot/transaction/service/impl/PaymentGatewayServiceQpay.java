package com.training.springboot.transaction.service.impl;

import com.training.springboot.transaction.dto.PaymentCreationDto;
import com.training.springboot.transaction.dto.PaymentDto;
import com.training.springboot.transaction.service.PaymentGatewayService;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayServiceQpay implements PaymentGatewayService {
    @Override
    public PaymentDto executePayment(PaymentCreationDto paymentCreationDto) {
        //TODO: call qpay API
        return null;
    }
}
