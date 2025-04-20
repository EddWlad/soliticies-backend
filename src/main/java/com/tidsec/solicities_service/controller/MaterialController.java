package com.tidsec.solicities_service.controller;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.cloudinary.json.JSONObject;
import com.cloudinary.*;
import com.tidsec.solicities_service.dtos.MaterialDTO;
import com.tidsec.solicities_service.entities.Material;

import com.tidsec.solicities_service.services.IMaterialService;
import com.tidsec.solicities_service.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;

import java.util.Collections;
import java.util.List;
import java.util.Map;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {
    private final Cloudinary cloudinary;
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

        Material obj = materialService.save(mapperUtil.map(materialDTO, Material.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdMaterial()).toUri();

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

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        File f = convertToFile(multipartFile);
        Map<String, Object> response =  cloudinary.uploader().upload(f, ObjectUtils.asMap("resource_type", "auto"));
        JSONObject json = new JSONObject(response);
        String url = json.getString("url");

        System.out.println(url);

        return ResponseEntity.ok(Collections.singletonList(url));

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

    private File convertToFile(MultipartFile multipartFile) throws Exception{
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(multipartFile.getBytes());
        outputStream.close();
        return file;
    }
}
