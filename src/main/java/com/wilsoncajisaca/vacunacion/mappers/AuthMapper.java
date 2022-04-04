package com.wilsoncajisaca.vacunacion.mappers;

import com.wilsoncajisaca.vacunacion.entities.Auth;
import com.wilsoncajisaca.vacunacion.entities.Employee;
import com.wilsoncajisaca.vacunacion.pojos.RegisterEmployeeINP;

/**
 * Mapping entity Auth
 */
public class AuthMapper {
    public static Auth toEntity(Long employeeId, String userName, String password){
        return Auth.builder()
                .idEmployee(employeeId)
                .username(userName)
                .password(password)
                .status(true)
                .build();
    }
}
