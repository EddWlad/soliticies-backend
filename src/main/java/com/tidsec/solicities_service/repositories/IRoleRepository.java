package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends IGenericRepository<Role, Long> {
}
