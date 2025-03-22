package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.AccrualDetail;
import com.tidsec.solicities_service.repositories.IAccrualDetailRepository;
import com.tidsec.solicities_service.repositories.IGenericRepository;

import com.tidsec.solicities_service.services.IAccrualDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccrualDetailServiceImpl extends GenericServiceImpl<AccrualDetail, Long> implements IAccrualDetailService {

    private IAccrualDetailRepository accrualDetailRepository;

    @Override
    protected IGenericRepository<AccrualDetail, Long> getRepo() {
        return accrualDetailRepository;
    }
}
