package com.training.springboot.transaction.dto.qpay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.training.springboot.transaction.constants.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QpayRequestDto {
    private String apiKey;
    private String apiSecret;
    private BigDecimal amount;
    private Bank bank;
}
