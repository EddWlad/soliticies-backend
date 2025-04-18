package com.tidsec.solicities_service.dtos;

import com.tidsec.solicities_service.entities.Material;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaFileImageDTO {
    @EqualsAndHashCode.Include
    private Long idImage;
    //private Material material;
    private String fileName;
    private String fileType;
    private byte[] content;
    private Integer status = 1;
}
