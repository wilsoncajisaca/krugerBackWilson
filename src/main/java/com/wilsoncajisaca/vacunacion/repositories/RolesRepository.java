package com.wilsoncajisaca.vacunacion.repositories;

import com.wilsoncajisaca.vacunacion.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
    List<Role> findByStatus(Boolean status);

    Optional<Role> findByName(String name);
}
