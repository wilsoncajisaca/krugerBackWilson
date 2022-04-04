package com.wilsoncajisaca.vacunacion.repositories;

import com.wilsoncajisaca.vacunacion.entities.VaccinationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaccineTypeRepository extends JpaRepository<VaccinationType, Long> {

    Optional<VaccinationType> findById(Long id);

}
