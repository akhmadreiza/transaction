package com.training.springboot.transaction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.training.springboot.transaction.constants.Bank;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentCreationDto {
    private BigDecimal amount;
    private Bank bank;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
