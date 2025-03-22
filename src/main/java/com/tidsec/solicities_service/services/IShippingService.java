package com.tidsec.solicities_service.services;

import com.tidsec.solicities_service.entities.Shipping;
import com.tidsec.solicities_service.entities.ShippingDetail;

import java.util.List;

public interface IShippingService extends IGenericService<Shipping, Long> {

    Shipping saveTransactional(Shipping shipping, List<ShippingDetail> shippingDetails) throws Exception;
}
