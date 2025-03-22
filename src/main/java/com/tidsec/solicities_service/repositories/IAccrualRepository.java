package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.Accrual;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccrualRepository extends IGenericRepository<Accrual, Long> {
}
