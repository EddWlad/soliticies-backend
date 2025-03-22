package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.Material;
import org.springframework.stereotype.Repository;

@Repository
public interface IMaterialRepository extends IGenericRepository<Material, Long> {
}
