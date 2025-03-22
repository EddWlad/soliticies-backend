package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.AccrualDTO;
import com.tidsec.solicities_service.dtos.AccrualListDetailDTO;
import com.tidsec.solicities_service.entities.Accrual;
import com.tidsec.solicities_service.entities.AccrualDetail;
import com.tidsec.solicities_service.services.IAccrualService;
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
@RequestMapping("/accruals")
@RequiredArgsConstructor
public class AccrualController {

    private final IAccrualService accrualService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todas las devengaciones
    @GetMapping
    public ResponseEntity<List<AccrualDTO>> findAll() throws Exception {
        List<AccrualDTO> list = mapperUtil.mapList(accrualService.findAll(), AccrualDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener una devengación por ID
    @GetMapping("/{id}")
    public ResponseEntity<AccrualDTO> findById(@PathVariable("id") Long id) throws Exception {
        AccrualDTO obj = mapperUtil.map(accrualService.findById(id), AccrualDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Crear una nueva devengación
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AccrualListDetailDTO dto) throws Exception {
        Accrual obj1 = accrualService.save(mapperUtil.map(dto.getAccrual(), Accrual.class));
        List<AccrualDetail> list = mapperUtil.mapList(dto.getAccrualDetails(), AccrualDetail.class);

        Accrual obj = accrualService.saveTransactional(obj1, list);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdAccrual()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar una devengación
    @PutMapping("/{id}")
    public ResponseEntity<AccrualDTO> update(@PathVariable("id") Long id, @RequestBody AccrualDTO accrualDTO) throws Exception {
        Accrual obj = accrualService.update(mapperUtil.map(accrualDTO, Accrual.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, AccrualDTO.class));
    }

    // 📌 5️⃣ Eliminar una devengación (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        accrualService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener una devengación con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<AccrualDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        Accrual obj = accrualService.findById(id);
        EntityModel<AccrualDTO> resource = EntityModel.of(mapperUtil.map(obj, AccrualDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("accrual-self-info"));
        resource.add(link2.withRel("accrual-all-info"));
        return resource;
    }

}
