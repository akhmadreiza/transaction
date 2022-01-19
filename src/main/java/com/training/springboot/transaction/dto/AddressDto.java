package com.training.springboot.transaction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private Long id;

    @NotBlank(message = "tidak boleh kosong")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "tidak boleh kosong")
    private String namaPenerima;

    @NotBlank(message = "tidak boleh kosong")
    private String kodePos;

    @NotNull(message = "tidak boleh kosong")
    private Boolean primaryAddress;

    @NotNull(message = "tidak boleh kosong")
    @Valid
    private CityDto city;
}
