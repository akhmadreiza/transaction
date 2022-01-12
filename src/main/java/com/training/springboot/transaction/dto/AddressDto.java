package com.training.springboot.transaction.dto;

import lombok.*;

@Data
@Builder
public class AddressDto {
    private String addressLine1;
    private String addressLine2;
}
