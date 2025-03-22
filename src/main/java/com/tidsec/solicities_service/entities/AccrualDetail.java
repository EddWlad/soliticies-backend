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
@Table(name= "accrual_detail")
public class AccrualDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idAccrualDetail;

    @Column(nullable = false)
    private Long idMaterial;

    @Column(nullable = false)
    private Double quantityUsed;

    @Column(nullable = false)
    private Integer status = 1;
}
