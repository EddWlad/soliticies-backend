package com.tidsec.solicities_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name= "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idMaterial;

    @Column(nullable = false)
    @Size(min = 3, max = 200)
    private String name;

    @Column(nullable = false)
    @Size(min = 3, max = 300)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_image", foreignKey = @ForeignKey(name = "FK_COMPANY_IMAGE"))
    private MediaFileImage image;

    @Column(nullable = false)
    private Double cost;

    @Column(nullable = false)
    private Integer status = 1;
}
