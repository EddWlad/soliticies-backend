package com.tidsec.solicities_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Table(name= "shipping_detail")
public class ShippingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idShippingDetail;

    @Column(nullable = false)
    private Long idMaterial;

    @Column(nullable = false)
    private Double quantityShipped;

    @Column(nullable = false)
    private Integer status = 1;
}
