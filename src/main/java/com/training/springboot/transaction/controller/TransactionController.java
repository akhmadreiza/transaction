package com.training.springboot.transaction.controller;

import com.training.springboot.transaction.dto.PaymentCreationDto;
import com.training.springboot.transaction.dto.PaymentDto;
import com.training.springboot.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/checkout", produces = "application/json")
    public PaymentDto checkout(@RequestBody PaymentCreationDto paymentCreationDto) {
        return transactionService.checkout(paymentCreationDto);
    }

    @GetMapping(produces = "application/json")
    public List<PaymentDto> transactionHistoryList() {
        return transactionService.getTransactions();
    }

    @PostMapping(value = "/callback", produces = "application/json")
    public PaymentDto transactionStatusCallback(@RequestBody PaymentDto paymentDto) {
        return transactionService.updateTransactionStatus(paymentDto.getTransactionId(), paymentDto.getTransactionStatus());
    }
}
