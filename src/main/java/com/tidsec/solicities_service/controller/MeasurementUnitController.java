package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.MeasurementUnitDTO;
import com.tidsec.solicities_service.entities.MeasurementUnit;
import com.tidsec.solicities_service.services.IMeasurementUnitService;
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
@RequestMapping("/measurement-units")
@RequiredArgsConstructor
public class MeasurementUnitController {

    private final IMeasurementUnitService measurementUnitService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todas las unidades de medida
    @GetMapping
    public ResponseEntity<List<MeasurementUnitDTO>> findAll() throws Exception {
        List<MeasurementUnitDTO> list = mapperUtil.mapList(measurementUnitService.findAll(), MeasurementUnitDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener una unidad de medida por ID
    @GetMapping("/{id}")
    public ResponseEntity<MeasurementUnitDTO> findById(@PathVariable("id") Long id) throws Exception {
        MeasurementUnitDTO obj = mapperUtil.map(measurementUnitService.findById(id), MeasurementUnitDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Crear una nueva unidad de medida
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody MeasurementUnitDTO measurementUnitDTO) throws Exception {
        MeasurementUnit obj = measurementUnitService.save(mapperUtil.map(measurementUnitDTO, MeasurementUnit.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdUnit()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar una unidad de medida
    @PutMapping("/{id}")
    public ResponseEntity<MeasurementUnitDTO> update(@PathVariable("id") Long id, @RequestBody MeasurementUnitDTO measurementUnitDTO) throws Exception {
        MeasurementUnit obj = measurementUnitService.update(mapperUtil.map(measurementUnitDTO, MeasurementUnit.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, MeasurementUnitDTO.class));
    }

    // 📌 5️⃣ Eliminar una unidad de medida (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        measurementUnitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener una unidad de medida con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<MeasurementUnitDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        MeasurementUnit obj = measurementUnitService.findById(id);
        EntityModel<MeasurementUnitDTO> resource = EntityModel.of(mapperUtil.map(obj, MeasurementUnitDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("measurement-unit-self-info"));
        resource.add(link2.withRel("measurement-unit-all-info"));
        return resource;
    }
}
