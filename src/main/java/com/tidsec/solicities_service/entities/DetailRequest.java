package com.tidsec.solicities_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name= "detail_request")
public class DetailRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idDetailRequest;

    @Column(nullable = false)
    private Long idMaterial;

    @Column(nullable = false)
    private Double quantityRequested;

    @Column(nullable = false)
    private Integer status = 1;

}
