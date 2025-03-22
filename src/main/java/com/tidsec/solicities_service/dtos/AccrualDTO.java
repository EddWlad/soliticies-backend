package com.tidsec.solicities_service.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tidsec.solicities_service.entities.StockTaking;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AccrualDTO {

    @EqualsAndHashCode.Include
    private Long idAccrual;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    private String description;
    private StockTaking stockTaking;
    private Integer status = 1;
}
