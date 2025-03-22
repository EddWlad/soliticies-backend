package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends IGenericRepository<Company, Long> {
}
