package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.MaterialDTO;
import com.tidsec.solicities_service.entities.Material;
import com.tidsec.solicities_service.entities.MediaFileImage;
import com.tidsec.solicities_service.services.IMaterialService;
import com.tidsec.solicities_service.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {
    private final IMaterialService materialService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todos los materiales
    @GetMapping
    public ResponseEntity<List<MaterialDTO>> findAll() throws Exception {
        List<MaterialDTO> list = mapperUtil.mapList(materialService.findAll(), MaterialDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener un material por ID
    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> findById(@PathVariable("id") Long id) throws Exception {
        MaterialDTO obj = mapperUtil.map(materialService.findById(id), MaterialDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Crear un nuevo material
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody MaterialDTO materialDTO) throws Exception {

        Material material = mapperUtil.map(materialDTO, Material.class);

        //!=
        if (materialDTO.getImages() == null && !materialDTO.getImages().isEmpty()) {
            List<MediaFileImage> images = materialDTO.getImages().stream().map(imageDTO -> {
                MediaFileImage image = mapperUtil.map(imageDTO, MediaFileImage.class);
                image.setContent(Base64.getDecoder().decode(imageDTO.getContent()));
                image.setMaterial(material);
                return image;
            }).collect(Collectors.toList());

            material.setMediaFileImageList(images);
        }

        Material savedMaterial = materialService.saveMaterialWithImages(material);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedMaterial.getIdMaterial()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar un material
    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> update(@PathVariable("id") Long id, @RequestBody MaterialDTO materialDTO) throws Exception {
        Material obj = materialService.update(mapperUtil.map(materialDTO, Material.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, MaterialDTO.class));
    }

    // 📌 5️⃣ Eliminar un material (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        materialService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener material con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<MaterialDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        Material obj = materialService.findById(id);
        EntityModel<MaterialDTO> resource = EntityModel.of(mapperUtil.map(obj, MaterialDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("material-self-info"));
        resource.add(link2.withRel("material-all-info"));
        return resource;
    }
}
