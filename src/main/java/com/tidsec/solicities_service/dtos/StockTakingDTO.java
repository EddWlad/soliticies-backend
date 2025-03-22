package com.tidsec.solicities_service.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tidsec.solicities_service.entities.Project;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StockTakingDTO {
    @EqualsAndHashCode.Include
    private Long idStockTaking;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated = LocalDateTime.now();

    private Project project;
    private Integer status = 1;
}
