package com.tidsec.solicities_service.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tidsec.solicities_service.entities.Material;
import com.tidsec.solicities_service.entities.Project;
import com.tidsec.solicities_service.entities.User;


import lombok.*;

import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MaterialsRequestDTO {
    @EqualsAndHashCode.Include
    private Long idMaterialsRequest;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated = LocalDateTime.now();

    private User residentEngineer;
    private Long idProject;
    private String description;
    private String observation;
    private Integer statusRequest = 1;
    private Integer status = 1;
}
