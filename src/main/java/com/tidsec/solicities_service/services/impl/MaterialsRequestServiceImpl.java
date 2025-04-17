package com.tidsec.solicities_service.services.impl;


import com.tidsec.solicities_service.dtos.DetailRequestDTO;
import com.tidsec.solicities_service.dtos.MaterialRequestListDetailDTO;
import com.tidsec.solicities_service.dtos.MaterialsRequestDTO;
import com.tidsec.solicities_service.entities.*;

import com.tidsec.solicities_service.repositories.*;
import com.tidsec.solicities_service.services.IMaterialsRequestService;
import com.tidsec.solicities_service.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialsRequestServiceImpl extends GenericServiceImpl<MaterialsRequest, Long> implements IMaterialsRequestService {

    private final IMaterialsRequestRepository materialsRequestRepository;
    private final IDetailRequestRepository detailRequestRepository;
    private final IUserRepository userRepository;
    private final IProjectRepository projectRepository;
    private final IMaterialRequestDetailRepository mdRepo;
    private final MapperUtil mapperUtil;


    @Override
    protected IGenericRepository<MaterialsRequest, Long> getRepo() {
        return materialsRequestRepository;
    }


    @Override
    public MaterialsRequest saveTransactional(MaterialsRequest materialsRequest,
                                              List<DetailRequest> detailRequests) throws Exception {
        // 1️⃣ Recuperar el usuario desde la base de datos antes de asignarlo
        if (materialsRequest.getResidentEngineer() != null && materialsRequest.getResidentEngineer().getIdUser() != null) {
            User user = userRepository.findById(materialsRequest.getResidentEngineer().getIdUser())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + materialsRequest.getResidentEngineer().getIdUser()));

            Project project = projectRepository.findById(materialsRequest.getIdProject())
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + materialsRequest.getIdProject()));

            materialsRequest.setResidentEngineer(user);
            materialsRequest.setIdProject(project.getIdProject());
        }

        materialsRequestRepository.save(materialsRequest);

        for (DetailRequest dr : detailRequests) {
            DetailRequest savedDetail = detailRequestRepository.save(dr); // Se asegura de que tiene ID
            mdRepo.saveDetail(materialsRequest.getIdMaterialsRequest(), savedDetail.getIdDetailRequest());
        }

        return materialsRequest;
    }

    @Override
    public List<MaterialRequestListDetailDTO> findAllWithDetails() throws Exception {
        List<MaterialsRequest> allRequests = materialsRequestRepository.findAll();

        return allRequests.stream().map(req -> {
            // Trae los registros de la tabla intermedia
            List<MaterialRequestDetail> links = mdRepo.findDetailsByRequestIdNative(req.getIdMaterialsRequest());

            // Extrae los DetailRequest
            List<DetailRequest> details = links.stream()
                    .map(MaterialRequestDetail::getDetailRequest)
                    .toList();

            return new MaterialRequestListDetailDTO(
                    mapperUtil.map(req, MaterialsRequestDTO.class),
                    mapperUtil.mapList(details, DetailRequestDTO.class)
            );
        }).toList();
    }

    @Override
    public MaterialRequestListDetailDTO findByIdWithDetails(Long id) throws Exception {
        MaterialsRequest req = materialsRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la solicitud con ID: " + id));

        List<MaterialRequestDetail> links = mdRepo.findDetailsByRequestIdNative(id);
        List<DetailRequest> details = links.stream()
                .map(MaterialRequestDetail::getDetailRequest)
                .toList();

        return new MaterialRequestListDetailDTO(
                mapperUtil.map(req, MaterialsRequestDTO.class),
                mapperUtil.mapList(details, DetailRequestDTO.class)
        );
    }
}
