package com.tidsec.solicities_service.services;

import com.tidsec.solicities_service.entities.Company;

public interface ICompanyService extends IGenericService<Company, Long> {
    Company saveCompanyWithLogo(Company company) throws Exception;
}
