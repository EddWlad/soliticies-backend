package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.ShippingDTO;
import com.tidsec.solicities_service.dtos.ShippingListDetailDTO;
import com.tidsec.solicities_service.entities.Shipping;
import com.tidsec.solicities_service.entities.ShippingDetail;
import com.tidsec.solicities_service.services.IShippingService;
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
@RequestMapping("/shipments")
@RequiredArgsConstructor
public class ShippingController {
    private final IShippingService shippingService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todos los envíos
    @GetMapping
    public ResponseEntity<List<ShippingDTO>> findAll() throws Exception {
        List<ShippingDTO> list = mapperUtil.mapList(shippingService.findAll(), ShippingDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener un envío por ID
    @GetMapping("/{id}")
    public ResponseEntity<ShippingDTO> findById(@PathVariable("id") Long id) throws Exception {
        ShippingDTO obj = mapperUtil.map(shippingService.findById(id), ShippingDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Crear un nuevo envío
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ShippingListDetailDTO dto) throws Exception {
        Shipping obj1 = shippingService.save(mapperUtil.map(dto.getShipping(), Shipping.class));
        List<ShippingDetail> list = mapperUtil.mapList(dto.getShippingDetails(), ShippingDetail.class);

        Shipping obj = shippingService.saveTransactional(obj1, list);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdShipping()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar un envío
    @PutMapping("/{id}")
    public ResponseEntity<ShippingDTO> update(@PathVariable("id") Long id, @RequestBody ShippingDTO shippingDTO) throws Exception {
        Shipping obj = shippingService.update(mapperUtil.map(shippingDTO, Shipping.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, ShippingDTO.class));
    }

    // 📌 5️⃣ Eliminar un envío (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        shippingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener un envío con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<ShippingDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        Shipping obj = shippingService.findById(id);
        EntityModel<ShippingDTO> resource = EntityModel.of(mapperUtil.map(obj, ShippingDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("shipping-self-info"));
        resource.add(link2.withRel("shipping-all-info"));
        return resource;
    }

}
