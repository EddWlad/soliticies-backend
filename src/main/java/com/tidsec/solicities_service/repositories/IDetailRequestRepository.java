package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.DetailRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetailRequestRepository extends IGenericRepository<DetailRequest, Long> {
}
