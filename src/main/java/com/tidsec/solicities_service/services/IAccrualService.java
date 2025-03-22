package com.tidsec.solicities_service.services;

import com.tidsec.solicities_service.entities.Accrual;
import com.tidsec.solicities_service.entities.AccrualDetail;

import java.util.List;

public interface IAccrualService extends IGenericService<Accrual, Long> {

    Accrual saveTransactional(Accrual accrual, List<AccrualDetail> accrualDetails) throws Exception;
}
