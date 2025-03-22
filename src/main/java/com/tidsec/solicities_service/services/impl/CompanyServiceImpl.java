package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.Company;
import com.tidsec.solicities_service.entities.MediaFileLogo;
import com.tidsec.solicities_service.repositories.ICompanyRepository;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IMediaFileLogoRepository;
import com.tidsec.solicities_service.services.ICompanyService;
import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public Company saveCompanyWithLogo(Company company) throws Exception {
        if (company == null || company.getLogo() == null) {
            throw new IllegalArgumentException("Company or Logo cannot  null");
        }

        MediaFileLogo mediaFileLogo = company.getLogo();
        mediaFileLogo.setCompany(company);

        companyRepository.save(company);
        mediaFileLogoRepository.save(mediaFileLogo);

        return company;
    }

}
