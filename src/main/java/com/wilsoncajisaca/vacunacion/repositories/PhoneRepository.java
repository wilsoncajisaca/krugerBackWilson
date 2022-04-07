package com.wilsoncajisaca.vacunacion.repositories;

import com.wilsoncajisaca.vacunacion.entities.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Spring data repository for the {@link MobilePhone} entity
 */
@Repository
public interface PhoneRepository extends JpaRepository<MobilePhone, Serializable> {
     Optional<MobilePhone> findByIdEmployeeAndNumberPhone(UUID employeeId, String numberPhone);
}
