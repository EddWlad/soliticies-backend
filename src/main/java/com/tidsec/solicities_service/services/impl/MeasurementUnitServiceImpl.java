package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.MeasurementUnit;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IMeasurementUnitRepository;
import com.tidsec.solicities_service.services.IMeasurementUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementUnitServiceImpl extends GenericServiceImpl<MeasurementUnit, Long> implements IMeasurementUnitService {

    private final IMeasurementUnitRepository measurementUnitRepository;

    @Override
    protected IGenericRepository<MeasurementUnit, Long> getRepo() {
        return measurementUnitRepository;
    }
}
