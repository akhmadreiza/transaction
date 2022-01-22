package com.training.springboot.transaction.service;

import com.training.springboot.transaction.dto.PaymentCreationDto;
import com.training.springboot.transaction.dto.PaymentDto;

public interface PaymentGatewayService {
    PaymentDto executePayment(PaymentCreationDto paymentCreationDto);
}
