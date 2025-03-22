package com.tidsec.solicities_service.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MeasurementUnitDTO {
    @EqualsAndHashCode.Include
    private Long idUnit;
    private String name;
    private Integer status = 1;
}
