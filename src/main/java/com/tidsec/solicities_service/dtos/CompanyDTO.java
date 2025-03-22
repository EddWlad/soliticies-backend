package com.tidsec.solicities_service.dtos;

import com.tidsec.solicities_service.entities.MediaFileLogo;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompanyDTO {
    @EqualsAndHashCode.Include
    private Long idCompany;
    private String ruc;
    private String name;
    private String address;
    private MediaFileLogo logo;
    private Integer status = 1;
}
