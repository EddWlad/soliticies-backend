package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.UserDTO;
import com.tidsec.solicities_service.entities.User;
import com.tidsec.solicities_service.services.IUserService;
import com.tidsec.solicities_service.util.MapperUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final IUserService userService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() throws Exception  {
        List<UserDTO> list = mapperUtil.mapList(userService.findAll(), UserDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) throws Exception {
        UserDTO obj = mapperUtil.map(userService.findById(id), UserDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UserDTO userDTO) throws Exception {
        User obj = userService.save(mapperUtil.map(userDTO, User.class));

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUser()).toUri();
        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) throws Exception  {
        User obj = userService.update(mapperUtil.map(userDTO, User.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, UserDTO.class));
    }

    // 📌 5️⃣ Eliminar un usuario (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener usuario con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<UserDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        User obj = userService.findById(id);
        EntityModel<UserDTO> resource = EntityModel.of(mapperUtil.map(obj, UserDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("user-self-info"));
        resource.add(link2.withRel("user-all-info"));
        return resource;
    }
}