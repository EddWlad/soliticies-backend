package com.tidsec.solicities_service.dtos;

import com.tidsec.solicities_service.entities.MeasurementUnit;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MaterialDTO {
    @EqualsAndHashCode.Include
    private Long idMaterial;
    private String name;
    private String description;
    private MeasurementUnit measurementUnit;
    private Double cost;
    private Integer status = 1;
    private List<MediaFileImageDTO> images;
}
