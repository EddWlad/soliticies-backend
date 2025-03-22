package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.Project;
import com.tidsec.solicities_service.entities.StockTaking;
import com.tidsec.solicities_service.entities.User;
import com.tidsec.solicities_service.entities.UserProject;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IProjectRepository;
import com.tidsec.solicities_service.repositories.IStockTakingRepository;
import com.tidsec.solicities_service.repositories.IUserProjectRepository;
import com.tidsec.solicities_service.services.IProjectService;
import com.tidsec.solicities_service.util.MapperUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl extends GenericServiceImpl<Project, Long> implements IProjectService {

    private final IProjectRepository projectRepository;

    private final IStockTakingRepository stockTakingRepository;

    private final IUserProjectRepository userProjectRepository;

    private final MapperUtil mapperUtil;

    @Override
    protected IGenericRepository<Project, Long> getRepo() {
        return projectRepository;
    }

    @Override
    @Transactional
    public Project saveProjectWithStockAndUsers(Project project, StockTaking stockTaking,
                                                User residentEngineer, User contractor) throws Exception {

        project = projectRepository.save(project);

        stockTaking.setProject(project);
        stockTaking.setDateCreated(LocalDateTime.now());
        stockTaking.setStatus(1);
        stockTakingRepository.save(stockTaking);

        userProjectRepository.assignUserToProject(residentEngineer.getIdUser(), project.getIdProject());
        userProjectRepository.assignUserToProject(contractor.getIdUser(), project.getIdProject());

        return project;
    }
}
