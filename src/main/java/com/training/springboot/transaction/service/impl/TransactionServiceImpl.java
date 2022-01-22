package com.training.springboot.transaction.service.impl;

import com.training.springboot.transaction.dto.PaymentCreationDto;
import com.training.springboot.transaction.dto.PaymentDto;
import com.training.springboot.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public PaymentDto checkout(PaymentCreationDto paymentCreationDto) {
        //TODO: call payment gateway service

        //TODO: insert initial transaction data with initial status PENDING
        return null;
    }

    @Override
    public List<PaymentDto> getTransactions() {
        //TODO: get transaction list from database
        return null;
    }

    @Override
    public PaymentDto updateTransactionStatus(String transactionId, String status) {
        //TODO: update transaction status to database
        return null;
    }
}
