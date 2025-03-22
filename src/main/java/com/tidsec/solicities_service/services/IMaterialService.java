package com.tidsec.solicities_service.services;

import com.tidsec.solicities_service.entities.Material;

public interface IMaterialService extends IGenericService<Material, Long> {
    Material saveMaterialWithImages(Material material) throws Exception;
}
