package com.training.springboot.transaction.repository;

import com.training.springboot.transaction.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    Optional<TransactionHistory> findByTransactionId(String transactionId);
}
