package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.DetailsShipping;
import com.tidsec.solicities_service.entities.DetailsShippingPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IDetailsShippingRepository extends IGenericRepository<DetailsShipping, DetailsShippingPK>{

    @Transactional
    @Modifying
    @Query(value="INSERT INTO details_shipping(id_shipping, id_shipping_detail, status) " +
            "VALUES (:idShipping, :idShippingDetail, 1)", nativeQuery = true)
    Integer saveDetail(@Param("idShipping") Long idShipping, @Param("idShippingDetail") Long idShippingDetail);
}
