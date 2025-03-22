package com.tidsec.solicities_service.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tidsec.solicities_service.entities.Company;
import com.tidsec.solicities_service.entities.StockTaking;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProjectDTO {
    @EqualsAndHashCode.Include
    private Long idProject;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated = LocalDateTime.now();

    private String nameProject;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateEnd;

    private String location;
    private String observation;
    private Company company;
    private Integer status = 1;
}
