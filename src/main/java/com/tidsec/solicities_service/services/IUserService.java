package com.tidsec.solicities_service.services;

import com.tidsec.solicities_service.entities.User;

public interface IUserService extends IGenericService<User, Long>{
    User findOneByUsername(String username);
    void changePassword(String username, String newPassword);
}
