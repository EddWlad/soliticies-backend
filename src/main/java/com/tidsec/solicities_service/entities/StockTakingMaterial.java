package com.tidsec.solicities_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StockTakingMaterialPK.class)
public class StockTakingMaterial {
    @Id
    private StockTaking stockTaking;

    @Id
    private Material material;

    @Column(nullable = false)
    private Double stock;

    @Column(nullable = false)
    private Integer status = 1;
}
