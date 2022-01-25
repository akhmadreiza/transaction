package com.training.springboot.transaction.service.impl;

import com.training.springboot.transaction.dto.PaymentCreationDto;
import com.training.springboot.transaction.dto.PaymentDto;
import com.training.springboot.transaction.entity.TransactionHistory;
import com.training.springboot.transaction.exception.DataNotFoundException;
import com.training.springboot.transaction.repository.TransactionHistoryRepository;
import com.training.springboot.transaction.service.PaymentGatewayService;
import com.training.springboot.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public PaymentDto checkout(PaymentCreationDto paymentCreationDto) {
        //call payment gateway service and construct response to controller
        PaymentDto paymentDto = paymentGatewayService.executePayment(paymentCreationDto);
        paymentDto.setAmount(paymentCreationDto.getAmount());
        paymentDto.setBank(paymentCreationDto.getBank());
        paymentDto.setTransactionStatus("PENDING");

        //insert initial transaction data with initial status PENDING
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionId(paymentDto.getTransactionId());
        transactionHistory.setTransactionStatus(paymentDto.getTransactionStatus());
        transactionHistory.setVirtualAccount(paymentDto.getVirtualAccount());
        transactionHistory.setAmount(paymentDto.getAmount());
        transactionHistory.setBank(paymentDto.getBank());
        transactionHistoryRepository.save(transactionHistory);
        return paymentDto;
    }

    @Override
    public List<PaymentDto> getTransactions() {
        //get transaction list from database
        List<TransactionHistory> transactionHistoryList = transactionHistoryRepository.findAll();
        List<PaymentDto> paymentDtoResult = new ArrayList<>();
        for (int i = 0; i < transactionHistoryList.size(); i++) {
            TransactionHistory eachTransactionHistory = transactionHistoryList.get(i);
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setTransactionId(eachTransactionHistory.getTransactionId());
            paymentDto.setTransactionStatus(eachTransactionHistory.getTransactionStatus());
            paymentDto.setVirtualAccount(eachTransactionHistory.getVirtualAccount());
            paymentDto.setAmount(eachTransactionHistory.getAmount());
            paymentDto.setBank(eachTransactionHistory.getBank());
            paymentDtoResult.add(paymentDto);
        }
        return paymentDtoResult;
    }

    @Override
    public PaymentDto updateTransactionStatus(String transactionId, String status) {
        //update transaction status to database
        Optional<TransactionHistory> optionalTransactionHistory = transactionHistoryRepository.findByTransactionId(transactionId);
        if (!optionalTransactionHistory.isPresent()) {
            throw new DataNotFoundException("Transaction not found");
        }
        TransactionHistory transactionHistory = optionalTransactionHistory.get();
        transactionHistory.setTransactionStatus(status);
        transactionHistoryRepository.save(transactionHistory);
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setTransactionId(transactionHistory.getTransactionId());
        paymentDto.setTransactionStatus(transactionHistory.getTransactionStatus());
        paymentDto.setVirtualAccount(transactionHistory.getVirtualAccount());
        paymentDto.setAmount(transactionHistory.getAmount());
        paymentDto.setBank(transactionHistory.getBank());
        return paymentDto;
    }
}
