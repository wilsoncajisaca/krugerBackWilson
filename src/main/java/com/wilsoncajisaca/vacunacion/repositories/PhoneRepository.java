package com.wilsoncajisaca.vacunacion.repositories;

import com.wilsoncajisaca.vacunacion.entities.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Spring data repository for the {@link MobilePhone} entity
 */
@Repository
public interface PhoneRepository extends JpaRepository<MobilePhone, Long> {
     Optional<MobilePhone> findByIdEmployeeAndNumberPhone(Long employeeId, String numberPhone);
}
