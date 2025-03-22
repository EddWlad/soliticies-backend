package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.MediaFileImage;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IMediaFileImageRepository;
import com.tidsec.solicities_service.services.IMediaFileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaFileImageServiceImpl extends GenericServiceImpl<MediaFileImage, Long> implements IMediaFileImageService {

    private final IMediaFileImageRepository mediaFileImageRepository;
    @Override
    protected IGenericRepository<MediaFileImage, Long> getRepo() {
        return mediaFileImageRepository;
    }
}
