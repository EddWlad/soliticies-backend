package com.tidsec.solicities_service.services;

import com.tidsec.solicities_service.dtos.MaterialRequestListDetailDTO;
import com.tidsec.solicities_service.entities.DetailRequest;

import com.tidsec.solicities_service.entities.MaterialsRequest;

import java.util.List;

public interface IMaterialsRequestService extends IGenericService<MaterialsRequest, Long> {

    MaterialsRequest saveTransactional(MaterialsRequest materialsRequest, List<DetailRequest> detailRequests) throws Exception;

    List<MaterialRequestListDetailDTO> findAllWithDetails() throws Exception;

    MaterialRequestListDetailDTO findByIdWithDetails(Long id) throws Exception;
}
