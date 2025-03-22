package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.MediaFileImageDTO;
import com.tidsec.solicities_service.entities.MediaFileImage;
import com.tidsec.solicities_service.services.IMediaFileImageService;
import com.tidsec.solicities_service.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/file-images")
@RequiredArgsConstructor
public class MediaFileImageController {

    private final IMediaFileImageService mediaFileImageService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todas las imágenes
    @GetMapping
    public ResponseEntity<List<MediaFileImageDTO>> findAll() throws Exception {
        List<MediaFileImageDTO> list = mapperUtil.mapList(mediaFileImageService.findAll(), MediaFileImageDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener una imagen por ID
    @GetMapping("/{id}")
    public ResponseEntity<MediaFileImageDTO> findById(@PathVariable("id") Long id) throws Exception {
        MediaFileImageDTO obj = mapperUtil.map(mediaFileImageService.findById(id), MediaFileImageDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Subir una nueva imagen
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody MediaFileImageDTO mediaFileImageDTO) throws Exception {
        MediaFileImage obj = mediaFileImageService.save(mapperUtil.map(mediaFileImageDTO, MediaFileImage.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdImage()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar una imagen
    @PutMapping("/{id}")
    public ResponseEntity<MediaFileImageDTO> update(@PathVariable("id") Long id, @RequestBody MediaFileImageDTO mediaFileImageDTO) throws Exception {
        MediaFileImage obj = mediaFileImageService.update(mapperUtil.map(mediaFileImageDTO, MediaFileImage.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, MediaFileImageDTO.class));
    }

    // 📌 5️⃣ Eliminar una imagen (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        mediaFileImageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener una imagen con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<MediaFileImageDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        MediaFileImage obj = mediaFileImageService.findById(id);
        EntityModel<MediaFileImageDTO> resource = EntityModel.of(mapperUtil.map(obj, MediaFileImageDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("media-file-self-info"));
        resource.add(link2.withRel("media-file-all-info"));
        return resource;
    }
}
