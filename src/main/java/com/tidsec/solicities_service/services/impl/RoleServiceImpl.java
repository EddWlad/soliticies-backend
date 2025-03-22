package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.Role;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IRoleRepository;
import com.tidsec.solicities_service.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements IRoleService {

    private final IRoleRepository roleRepository;

    @Override
    protected IGenericRepository<Role, Long> getRepo() {
        return roleRepository;
    }

}
