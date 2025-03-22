package com.tidsec.solicities_service.dtos;


import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String description;

    private Integer status = 1;
}
