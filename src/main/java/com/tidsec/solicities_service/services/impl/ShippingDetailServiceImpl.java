package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.ShippingDetail;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IShippingDetailRepository;
import com.tidsec.solicities_service.services.IShippingDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingDetailServiceImpl extends GenericServiceImpl<ShippingDetail, Long> implements IShippingDetailService {

    private final IShippingDetailRepository shippingDetailRepository;

    @Override
    protected IGenericRepository<ShippingDetail, Long> getRepo() {
        return shippingDetailRepository;
    }
}
