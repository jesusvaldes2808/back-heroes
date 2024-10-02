package com.application.rest.jwtauthlogin.dao;

import com.application.rest.jwtauthlogin.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying()
    @Transactional
    @Query("UPDATE User u set u.name=:name, u.username=:username, u.password=:password WHERE u.id = :id")
    void updateUser(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("username") String username,
            @Param("password") String password
    );
}
