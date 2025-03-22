package com.tidsec.solicities_service.controller;

import com.tidsec.solicities_service.dtos.ProjectDTO;
import com.tidsec.solicities_service.dtos.ProjectFullDTO;
import com.tidsec.solicities_service.entities.Project;
import com.tidsec.solicities_service.entities.StockTaking;
import com.tidsec.solicities_service.entities.User;
import com.tidsec.solicities_service.exception.ModelNotFoundException;
import com.tidsec.solicities_service.services.IProjectService;
import com.tidsec.solicities_service.services.IUserService;
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
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final IProjectService projectService;
    private final IUserService userService;
    private final MapperUtil mapperUtil;

    // 📌 1️⃣ Obtener todos los proyectos
    @GetMapping
    public ResponseEntity<List<ProjectDTO>> findAll() throws Exception {
        List<ProjectDTO> list = mapperUtil.mapList(projectService.findAll(), ProjectDTO.class);
        return ResponseEntity.ok(list);
    }

    // 📌 2️⃣ Obtener un proyecto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable("id") Long id) throws Exception {
        ProjectDTO obj = mapperUtil.map(projectService.findById(id), ProjectDTO.class);
        return ResponseEntity.ok(obj);
    }

    // 📌 3️⃣ Crear un nuevo proyecto con su inventario
    @PostMapping
    public ResponseEntity<Void> saveProjectWithStock(@RequestBody ProjectFullDTO dto) throws Exception {

        // 📌 Convertir DTO a Entidad
        Project project = mapperUtil.map(dto.getProject(), Project.class);
        StockTaking stockTaking = mapperUtil.map(dto.getStockTaking(), StockTaking.class);

// 📌 Obtener los Usuarios desde la BD (Ingeniero Residente y Contratista)
        User residentEngineer = userService.findById(dto.getIdResidentEngineer());
        if (residentEngineer == null) {
            throw new ModelNotFoundException("Ingeniero Residente no encontrado con ID: " + dto.getIdResidentEngineer());
        }

        User contractor = userService.findById(dto.getIdContractor());
        if (contractor == null) {
            throw new ModelNotFoundException("Contratista no encontrado con ID: " + dto.getIdContractor());
        }

        // 📌 Llamar al servicio para guardar el Proyecto, el Inventario y Asignar los Usuarios
        Project savedProject = projectService.saveProjectWithStockAndUsers(project, stockTaking, residentEngineer, contractor);

        // 📌 Crear la URI de respuesta
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedProject.getIdProject()).toUri();

        return ResponseEntity.created(location).build();
    }

    // 📌 4️⃣ Actualizar un proyecto
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable("id") Long id, @RequestBody ProjectDTO projectDTO) throws Exception {
        Project obj = projectService.update(mapperUtil.map(projectDTO, Project.class), id);
        return ResponseEntity.ok(mapperUtil.map(obj, ProjectDTO.class));
    }

    // 📌 5️⃣ Eliminar un proyecto (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📌 6️⃣ Obtener un proyecto con HATEOAS
    @GetMapping("/hateoas/{id}")
    public EntityModel<ProjectDTO> findByIdHateoas(@PathVariable("id") Long id) throws Exception {
        Project obj = projectService.findById(id);
        EntityModel<ProjectDTO> resource = EntityModel.of(mapperUtil.map(obj, ProjectDTO.class));

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("project-self-info"));
        resource.add(link2.withRel("project-all-info"));
        return resource;
    }
}
