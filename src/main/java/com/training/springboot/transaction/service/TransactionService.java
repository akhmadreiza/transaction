package com.training.springboot.transaction.service;

import com.training.springboot.transaction.dto.PaymentCreationDto;
import com.training.springboot.transaction.dto.PaymentDto;

import java.util.List;

public interface TransactionService {
    PaymentDto checkout(PaymentCreationDto paymentCreationDto);

    List<PaymentDto> getTransactions();

    PaymentDto updateTransactionStatus(String transactionId, String status);
}
