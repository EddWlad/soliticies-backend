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

    @Override
    @Transactional
    public Material saveMaterialWithImages(Material material) throws Exception {
        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null");
        }

        Material savedMaterial = materialRepository.save(material);

        if (material.getMediaFileImageList() != null && !material.getMediaFileImageList().isEmpty()) {
            for (MediaFileImage image : material.getMediaFileImageList()) {
                image.setMaterial(savedMaterial);
                mediaFileImageRepository.save(image);
            }
        }
        return savedMaterial;
    }
}
