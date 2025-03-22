package com.tidsec.solicities_service.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccrualDetailDTO {

    private Long idAccrualDetail;

    @NotNull
    private Long idMaterial;

    @NotNull
    private Double quantityUsed;

    private Integer status = 1;
}
