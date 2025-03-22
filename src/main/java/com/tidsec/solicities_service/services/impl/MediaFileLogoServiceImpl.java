package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.MediaFileLogo;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IMediaFileLogoRepository;
import com.tidsec.solicities_service.services.IMediaFileLogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaFileLogoServiceImpl extends GenericServiceImpl<MediaFileLogo, Long> implements IMediaFileLogoService {

    private final IMediaFileLogoRepository mediaFileLogoRepository;

    @Override
    protected IGenericRepository<MediaFileLogo, Long> getRepo() {
        return mediaFileLogoRepository;
    }
}
