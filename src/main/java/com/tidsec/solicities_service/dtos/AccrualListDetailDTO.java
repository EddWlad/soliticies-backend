package com.tidsec.solicities_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccrualListDetailDTO {
    private AccrualDTO accrual;
    private List<AccrualDetailDTO> accrualDetails;
}
