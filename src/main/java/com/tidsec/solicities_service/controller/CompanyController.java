package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.CompanyDTO;
import com.tidsec.solicities_service.entities.Company;
import com.tidsec.solicities_service.entities.MediaFileLogo;
import com.tidsec.solicities_service.services.ICompanyService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService companyService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todas las empresas
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAll() throws Exception  {
        List<CompanyDTO> list = mapperUtil.mapList(companyService.findAll(), CompanyDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener una empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable("id") Long id) throws Exception  {
        CompanyDTO obj = mapperUtil.map(companyService.findById(id), CompanyDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Crear una nueva empresa
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CompanyDTO companyDTO) throws Exception  {

        Company obj = companyService.save(mapperUtil.map(companyDTO, Company.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdCompany()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar una empresa
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable("id") Long id, @RequestBody CompanyDTO companyDTO) throws Exception  {
        Company obj = companyService.update(mapperUtil.map(companyDTO, Company.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, CompanyDTO.class));
    }

    // 📌 5️⃣ Eliminar una empresa (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception  {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener empresa con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<CompanyDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception  {
        Company obj = companyService.findById(id);
        EntityModel<CompanyDTO> resource = EntityModel.of(mapperUtil.map(obj, CompanyDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("company-self-info"));
        resource.add(link2.withRel("company-all-info"));
        return resource;
    }
}
