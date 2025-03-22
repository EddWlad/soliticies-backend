package com.tidsec.solicities_service.services;

import com.tidsec.solicities_service.entities.Project;
import com.tidsec.solicities_service.entities.StockTaking;
import com.tidsec.solicities_service.entities.User;

public interface IProjectService extends IGenericService<Project, Long> {
    Project saveProjectWithStockAndUsers(Project project, StockTaking stockTaking,
                                         User residentEngineer, User contractor) throws Exception;
}
