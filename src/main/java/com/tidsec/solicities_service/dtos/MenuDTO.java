package com.tidsec.solicities_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private Integer idMenu;
    private String icon;
    private String name;
    private String url;
    private Integer status = 1;
}

