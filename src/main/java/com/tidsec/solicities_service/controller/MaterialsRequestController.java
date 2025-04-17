package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.MaterialRequestListDetailDTO;
import com.tidsec.solicities_service.dtos.MaterialsRequestDTO;
import com.tidsec.solicities_service.entities.DetailRequest;
import com.tidsec.solicities_service.entities.MaterialsRequest;
import com.tidsec.solicities_service.services.IMaterialsRequestService;
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
@RequestMapping("/materials-requests")
@RequiredArgsConstructor
public class MaterialsRequestController {

    private final IMaterialsRequestService materialsRequestService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todas las solicitudes de materiales con su detalle
    @GetMapping
    public ResponseEntity<List<MaterialRequestListDetailDTO>> findAll() throws Exception {
        List<MaterialRequestListDetailDTO> list = materialsRequestService.findAllWithDetails();
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener una solicitud de materiales por ID
    @GetMapping("/{id}")
    public ResponseEntity<MaterialRequestListDetailDTO> findById(@PathVariable("id") Long id) throws Exception {
        MaterialRequestListDetailDTO dto = materialsRequestService.findByIdWithDetails(id);
        return ResponseEntity.ok(dto);
    }

    // 📌 3️⃣ Crear una nueva solicitud de materiales
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody MaterialRequestListDetailDTO dto) throws Exception {
        MaterialsRequest obj1 = mapperUtil.map(dto.getMaterialsRequest(), MaterialsRequest.class);
        List<DetailRequest> list = mapperUtil.mapList(dto.getDetailRequests(), DetailRequest.class);

        MaterialsRequest obj = materialsRequestService.saveTransactional(obj1, list);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdMaterialsRequest()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar una solicitud de materiales
    @PutMapping("/{id}")
    public ResponseEntity<MaterialsRequestDTO> update(@PathVariable("id") Long id, @RequestBody MaterialsRequestDTO materialsRequestDTO) throws Exception {
        MaterialsRequest obj = materialsRequestService.update(mapperUtil.map(materialsRequestDTO, MaterialsRequest.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, MaterialsRequestDTO.class));
    }

    // 📌 5️⃣ Eliminar una solicitud de materiales (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        materialsRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener una solicitud de materiales con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<MaterialsRequestDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        MaterialsRequest obj = materialsRequestService.findById(id);
        EntityModel<MaterialsRequestDTO> resource = EntityModel.of(mapperUtil.map(obj, MaterialsRequestDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("materials-request-self-info"));
        resource.add(link2.withRel("materials-request-all-info"));
        return resource;
    }
}
