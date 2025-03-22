package com.tidsec.solicities_service.entities;

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
@IdClass(AccrualDetailsPK.class)
public class AccrualDetails {
    @Id
    private Accrual accrual;

    @Id
    private AccrualDetail accrualDetail;

    private Integer status = 1;
}
