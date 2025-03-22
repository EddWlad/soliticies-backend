package com.tidsec.solicities_service.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class AccrualDetailsPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_accrual", foreignKey = @ForeignKey(name = "FK_ACCRUAL_DETAIL_A"))
    private Accrual accrual;

    @ManyToOne
    @JoinColumn(name = "id_accrual_detail", foreignKey = @ForeignKey(name = "FK_ACCRUAL_DETAIL_D"))
    private AccrualDetail accrualDetail;

}
