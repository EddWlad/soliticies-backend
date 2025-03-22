package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.StockTakingDTO;
import com.tidsec.solicities_service.entities.StockTaking;
import com.tidsec.solicities_service.services.IStockTakingService;
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
@RequestMapping("/stock-taking")
@RequiredArgsConstructor
public class StockTakingController {

    private final IStockTakingService stockTakingService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todos los inventarios
    @GetMapping
    public ResponseEntity<List<StockTakingDTO>> findAll() throws Exception {
        List<StockTakingDTO> list = mapperUtil.mapList(stockTakingService.findAll(), StockTakingDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener un inventario por ID
    @GetMapping("/{id}")
    public ResponseEntity<StockTakingDTO> findById(@PathVariable("id") Long id) throws Exception {
        StockTakingDTO obj = mapperUtil.map(stockTakingService.findById(id), StockTakingDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Crear un nuevo inventario
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody StockTakingDTO stockTakingDTO) throws Exception {
        StockTaking obj = stockTakingService.save(mapperUtil.map(stockTakingDTO, StockTaking.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdStockTaking()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar un inventario
    @PutMapping("/{id}")
    public ResponseEntity<StockTakingDTO> update(@PathVariable("id") Long id, @RequestBody StockTakingDTO stockTakingDTO) throws Exception {
        StockTaking obj = stockTakingService.update(mapperUtil.map(stockTakingDTO, StockTaking.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, StockTakingDTO.class));
    }

    // 📌 5️⃣ Eliminar un inventario (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        stockTakingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener un inventario con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<StockTakingDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        StockTaking obj = stockTakingService.findById(id);
        EntityModel<StockTakingDTO> resource = EntityModel.of(mapperUtil.map(obj, StockTakingDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("stock-taking-self-info"));
        resource.add(link2.withRel("stock-taking-all-info"));
        return resource;
    }
}
