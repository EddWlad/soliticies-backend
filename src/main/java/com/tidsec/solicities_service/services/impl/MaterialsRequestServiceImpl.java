package com.tidsec.solicities_service.services.impl;


import com.tidsec.solicities_service.entities.DetailRequest;

import com.tidsec.solicities_service.entities.MaterialsRequest;
import com.tidsec.solicities_service.entities.Project;
import com.tidsec.solicities_service.entities.User;
import com.tidsec.solicities_service.repositories.*;
import com.tidsec.solicities_service.services.IMaterialsRequestService;
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
}
