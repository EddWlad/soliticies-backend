package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.Accrual;
import com.tidsec.solicities_service.repositories.IAccrualRepository;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.services.IAccrualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccrualServiceImpl extends GenericServiceImpl<Accrual, Long> implements IAccrualService {

    private final IAccrualRepository accrualRepository;

    @Override
    protected IGenericRepository<Accrual, Long> getRepo() {
        return accrualRepository;
    }
}
