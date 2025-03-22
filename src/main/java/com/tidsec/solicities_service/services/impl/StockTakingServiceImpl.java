package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.Material;
import com.tidsec.solicities_service.entities.StockTaking;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IStockTakingRepository;
import com.tidsec.solicities_service.services.IStockTakingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockTakingServiceImpl extends GenericServiceImpl<StockTaking, Long> implements IStockTakingService {

    private final IStockTakingRepository stockTakingRepository;

    @Override
    protected IGenericRepository<StockTaking, Long> getRepo() {
        return stockTakingRepository;
    }

}
