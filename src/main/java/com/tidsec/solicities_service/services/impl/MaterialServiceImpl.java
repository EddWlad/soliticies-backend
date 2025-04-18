package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.Material;
import com.tidsec.solicities_service.entities.MediaFileImage;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IMaterialRepository;
import com.tidsec.solicities_service.repositories.IMediaFileImageRepository;
import com.tidsec.solicities_service.services.IMaterialService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl extends GenericServiceImpl<Material, Long> implements IMaterialService {

    private final IMaterialRepository materialRepository;
    private final IMediaFileImageRepository mediaFileImageRepository;

    @Override
    protected IGenericRepository<Material, Long> getRepo() {
        return materialRepository;
    }

}
