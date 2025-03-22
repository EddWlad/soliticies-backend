package com.tidsec.solicities_service.repositories;

import com.tidsec.solicities_service.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IGenericRepository<User, Long> {
    User findOneByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User us SET us.password = :password WHERE us.username = :username")
    void changePassword(@Param("username") String username, @Param("password") String newPassword);
}
