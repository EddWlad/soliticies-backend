package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.ShippingDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface IShippingDetailRepository extends IGenericRepository<ShippingDetail, Long> {
}
