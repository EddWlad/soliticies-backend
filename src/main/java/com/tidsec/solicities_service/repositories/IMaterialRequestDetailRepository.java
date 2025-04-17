package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.MaterialRequestDetail;
import com.tidsec.solicities_service.entities.MaterialRequestDetailPK;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMaterialRequestDetailRepository extends IGenericRepository<MaterialRequestDetail, MaterialRequestDetailPK> {

    @Transactional
    @Modifying
    @Query(value="INSERT INTO material_request_detail(id_materials_request, id_detail_request, status) " +
            "VALUES (:idMaterialsRequest, :idDetailRequest, 1)", nativeQuery = true)
    Integer saveDetail(@Param("idMaterialsRequest") Long idMaterialsRequest, @Param("idDetailRequest") Long idDetailRequest);

    @Query(value = "SELECT * FROM material_request_detail WHERE id_materials_request = :idMaterialsRequest AND status = 1", nativeQuery = true)
    List<MaterialRequestDetail> findDetailsByRequestIdNative(@Param("idMaterialsRequest") Long idMaterialsRequest);



}
