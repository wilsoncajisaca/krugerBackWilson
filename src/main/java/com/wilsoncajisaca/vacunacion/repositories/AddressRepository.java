package com.wilsoncajisaca.vacunacion.repositories;

import com.wilsoncajisaca.vacunacion.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Spring data repository for the {@link Address} entity
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Serializable> {

}