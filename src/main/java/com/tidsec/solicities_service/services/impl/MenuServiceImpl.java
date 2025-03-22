package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.Menu;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IMenuRepository;
import com.tidsec.solicities_service.services.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends GenericServiceImpl<Menu, Long> implements IMenuService {

    private final IMenuRepository menuRepository;

    @Override
    protected IGenericRepository<Menu, Long> getRepo() {
        return menuRepository;
    }

    @Override
    public List<Menu> getMenusByUsername(String username) {
        return menuRepository.getMenusByUsername(username);
    }


}