package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.Shipping;
import org.springframework.stereotype.Repository;

@Repository
public interface IShippingRepository extends IGenericRepository<Shipping, Long> {
}
