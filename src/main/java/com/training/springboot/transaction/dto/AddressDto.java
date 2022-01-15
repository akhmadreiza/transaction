package com.training.springboot.transaction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private Long id;

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String namaPenerima;

    @NotBlank
    private String kodePos;

    @NotNull
    private Boolean primaryAddress;
}
