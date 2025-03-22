package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.UserProject;
import com.tidsec.solicities_service.entities.UserProjectPK;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IUserProjectRepository extends IGenericRepository<UserProject, UserProjectPK>{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_project (id_user, id_project, status) VALUES (:idUser, :idProject, 1)", nativeQuery = true)
    Integer assignUserToProject(@Param("idUser") Long idUser, @Param("idProject") Long idProject);

}
