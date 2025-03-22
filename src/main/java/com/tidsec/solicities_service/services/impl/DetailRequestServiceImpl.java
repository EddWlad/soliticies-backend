package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.DetailRequest;
import com.tidsec.solicities_service.repositories.IDetailRequestRepository;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.services.IDetailRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailRequestServiceImpl extends GenericServiceImpl<DetailRequest, Long> implements IDetailRequestService {

    private final IDetailRequestRepository detailRequestRepository;

    @Override
    protected IGenericRepository<DetailRequest, Long> getRepo() {
        return detailRequestRepository;
    }
}
