package com.tidsec.solicities_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_unit", foreignKey = @ForeignKey(name = "FK_MATERIAL_UNIT"))
    private MeasurementUnit measurementUnit;

    @Column(nullable = false)
    private Double cost;

    private List<String> images;

    @Column(nullable = false)
    private Integer status = 1;
}
