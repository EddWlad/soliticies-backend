package com.tidsec.solicities_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(DetailsShippingPK.class)
public class DetailsShipping {
    @Id
    private Shipping shipping;

    @Id
    private ShippingDetail shippingDetail;

    @Column
    private Integer status = 1;
}
