package com.wilsoncajisaca.vacunacion.repositories;

import com.wilsoncajisaca.vacunacion.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Serializable> {
    Optional<Auth> findByUsernameAndStatus(String userName, Boolean status);

    Optional<Auth> findByUsername(String username);
}
