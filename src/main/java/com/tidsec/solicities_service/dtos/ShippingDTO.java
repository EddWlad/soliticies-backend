package com.tidsec.solicities_service.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tidsec.solicities_service.entities.MaterialsRequest;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ShippingDTO {
    @EqualsAndHashCode.Include
    private Long idShipping;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated = LocalDateTime.now();

    private MaterialsRequest materialsRequest;
    private String observation;
    private Integer status = 1;
}
