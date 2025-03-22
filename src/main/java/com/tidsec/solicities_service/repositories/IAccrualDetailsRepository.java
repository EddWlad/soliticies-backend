package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.AccrualDetails;
import com.tidsec.solicities_service.entities.AccrualDetailsPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IAccrualDetailsRepository extends IGenericRepository<AccrualDetails, AccrualDetailsPK>{

    @Transactional
    @Modifying
    @Query(value="INSERT INTO accrual_details(id_accrual, id_accrual_detail, status) " +
            "VALUES (:idAccrual, :idAccrualDetail, 1)", nativeQuery = true)
    Integer saveDetail(@Param("idAccrual") Long idAccrual, @Param("idAccrualDetail") Long idAccrualDetail);
}
