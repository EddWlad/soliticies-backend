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

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl extends GenericServiceImpl<Material, Long> implements IMaterialService {

    private final IMaterialRepository materialRepository;

    @Override
    protected IGenericRepository<Material, Long> getRepo() {
        return materialRepository;
    }

    /*@Override
    public void updatePhoto(Long id, List<String> urlPhoto) throws Exception {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new Exception("Material not found"));
        material.setImages(urlPhoto);
        materialRepository.save(material);
    }*/
}
