package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.Company;

import com.tidsec.solicities_service.repositories.ICompanyRepository;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IMediaFileLogoRepository;
import com.tidsec.solicities_service.services.ICompanyService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends GenericServiceImpl<Company, Long> implements ICompanyService {

    private final ICompanyRepository companyRepository;
    private final IMediaFileLogoRepository mediaFileLogoRepository;

    @Override
    protected IGenericRepository<Company, Long> getRepo() {
        return companyRepository;
    }

}
