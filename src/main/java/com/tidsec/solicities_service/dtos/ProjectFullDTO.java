package com.tidsec.solicities_service.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProjectFullDTO {
    private ProjectDTO project;
    private StockTakingDTO stockTaking;
    private Long idResidentEngineer;
    private Long idContractor;
}
