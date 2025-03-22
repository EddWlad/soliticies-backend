package com.tidsec.solicities_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailRequestDTO {

    private Long idDetailRequest;

    @NotNull
    private Long idMaterial;

    @NotNull
    private Double quantityRequested;

    @NotNull
    private Integer status = 1;
}
