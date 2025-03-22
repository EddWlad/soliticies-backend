package com.tidsec.solicities_service.services;

import com.tidsec.solicities_service.entities.Menu;

import java.util.List;

public interface IMenuService {
    List<Menu> getMenusByUsername(String username);
}
