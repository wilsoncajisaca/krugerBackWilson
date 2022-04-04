package com.wilsoncajisaca.vacunacion.service;

import com.wilsoncajisaca.vacunacion.pojos.AuthINP;

import java.util.Map;

/**
 * Service interface for managing
 * {@link com.wilsoncajisaca.vacunacion.entities.Auth}
 */
public interface AuthService {

    /**
     * Save a new employee
     *
     * @param auth the entity data to save
     * @return the persisted entity
     */
    Map<String, Object> autenticate(AuthINP auth) throws Exception;

}
