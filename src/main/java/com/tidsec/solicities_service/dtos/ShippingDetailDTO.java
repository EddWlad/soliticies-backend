package com.tidsec.solicities_service.dtos;

import jakarta.persistence.Column;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDetailDTO {

    private Long idShippingDetail;

    @NotNull
    private Long idMaterial;

    @NotNull
    private Double quantityShipped;

    private Integer status = 1;
}
