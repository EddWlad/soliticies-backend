package com.tidsec.solicities_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name= "mediaFile_image")
public class MediaFileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idImage;
    @Column(length = 50, nullable = false)
    private String fileName;
    @Column(length = 20, nullable = false)
    private String fileType;
    @Column(nullable = false)
    private byte[] content;
    @Column(nullable = false)
    private Integer status = 1;
}
