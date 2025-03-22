package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends IGenericRepository<Project, Long> {
}
