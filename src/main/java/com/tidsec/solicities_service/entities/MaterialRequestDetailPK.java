package com.tidsec.solicities_service.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class MaterialRequestDetailPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_materials_request", foreignKey = @ForeignKey(name = "FK_MATERIALS_REQUEST_DETAIL_MR"))
    private MaterialsRequest materialsRequest;

    @ManyToOne
    @JoinColumn(name = "id_detail_request", foreignKey = @ForeignKey(name = "FK_MATERIALS_REQUEST_DETAIL_DR"))
    private DetailRequest detailRequest;

}
