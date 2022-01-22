package com.training.springboot.transaction.repository;

import com.training.springboot.transaction.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    TransactionHistory findByTransactionId(String transactionId);
}
