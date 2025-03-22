package com.tidsec.solicities_service.services.impl;

import com.tidsec.solicities_service.entities.User;
import com.tidsec.solicities_service.repositories.IGenericRepository;
import com.tidsec.solicities_service.repositories.IUserRepository;
import com.tidsec.solicities_service.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements IUserService {

    private final IUserRepository userRepository;
    //private final PasswordEncoder bcrypt;

    @Override
    protected IGenericRepository<User, Long> getRepo() {
        return userRepository;
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        //userRepository.changePassword(username, bcrypt.encode(newPassword)) ;
    }


}