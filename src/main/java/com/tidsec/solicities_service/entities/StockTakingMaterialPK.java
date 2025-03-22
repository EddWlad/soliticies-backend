package com.tidsec.solicities_service.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class StockTakingMaterialPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_stockTaking", foreignKey = @ForeignKey(name = "FK_STOCKTAKING_MATERIAL_ST"))
    private StockTaking stockTaking;

    @ManyToOne
    @JoinColumn(name = "id_material", foreignKey = @ForeignKey(name = "FK_STOCKTAKING_MATERIAL_M"))
    private Material material;
}
