package com.tidsec.solicities_service.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class DetailsShippingPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_shipping", foreignKey = @ForeignKey(name = "FK_SHIPPING_DETAIL_S"))
    private Shipping shipping;

    @ManyToOne
    @JoinColumn(name = "id_shipping_detail", foreignKey = @ForeignKey(name = "FK_SHIPPING_DETAIL_SD"))
    private ShippingDetail shippingDetail;
}
