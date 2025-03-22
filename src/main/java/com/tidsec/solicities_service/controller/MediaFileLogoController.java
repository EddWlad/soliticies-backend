package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.MediaFileLogoDTO;
import com.tidsec.solicities_service.entities.MediaFileLogo;
import com.tidsec.solicities_service.services.IMediaFileLogoService;
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
@RequestMapping("/file-logos")
@RequiredArgsConstructor
public class MediaFileLogoController {

    private final IMediaFileLogoService mediaFileLogoService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todos los logos
    @GetMapping
    public ResponseEntity<List<MediaFileLogoDTO>> findAll() throws Exception {
        List<MediaFileLogoDTO> list = mapperUtil.mapList(mediaFileLogoService.findAll(), MediaFileLogoDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener un logo por ID
    @GetMapping("/{id}")
    public ResponseEntity<MediaFileLogoDTO> findById(@PathVariable("id") Long id) throws Exception {
        MediaFileLogoDTO obj = mapperUtil.map(mediaFileLogoService.findById(id), MediaFileLogoDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Subir un nuevo logo
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody MediaFileLogoDTO mediaFileLogoDTO) throws Exception {
        MediaFileLogo obj = mediaFileLogoService.save(mapperUtil.map(mediaFileLogoDTO, MediaFileLogo.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdFile()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar un logo
    @PutMapping("/{id}")
    public ResponseEntity<MediaFileLogoDTO> update(@PathVariable("id") Long id, @RequestBody MediaFileLogoDTO mediaFileLogoDTO) throws Exception {
        MediaFileLogo obj = mediaFileLogoService.update(mapperUtil.map(mediaFileLogoDTO, MediaFileLogo.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, MediaFileLogoDTO.class));
    }

    // 📌 5️⃣ Eliminar un logo (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        mediaFileLogoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener un logo con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<MediaFileLogoDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        MediaFileLogo obj = mediaFileLogoService.findById(id);
        EntityModel<MediaFileLogoDTO> resource = EntityModel.of(mapperUtil.map(obj, MediaFileLogoDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("media-file-logo-self-info"));
        resource.add(link2.withRel("media-file-logo-all-info"));
        return resource;
    }
}
