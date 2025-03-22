package com.tidsec.solicities_service.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaFileLogoDTO {
    @EqualsAndHashCode.Include
    private Long idFile;
    private String fileName;
    private String fileType;
    private byte[] content;
    private Integer status = 1;
}
