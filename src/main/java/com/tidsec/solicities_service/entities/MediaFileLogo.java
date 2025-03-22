package com.tidsec.solicities_service.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name= "mediaFile_logo")
public class MediaFileLogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idFile;

    @Column(length = 50, nullable = false)
    private String fileName;

    @Column(length = 20, nullable = false)
    private String fileType;

    @Column(nullable = false)
    private byte[] content;

    @OneToOne
    @JoinColumn(name = "id_company", foreignKey = @ForeignKey(name = "FK_LOGO_COMPANY"))
    @JsonIgnore
    private Company company;

    @Column(nullable = false)
    private Integer status = 1;
}
