package com.wilsoncajisaca.vacunacion.mappers;

import com.wilsoncajisaca.vacunacion.entities.Employee;
import com.wilsoncajisaca.vacunacion.pojos.RegisterEmployeeINP;

/**
 * Mapping entity employee
 */
public class EmployeeMapper {
    /**
     * Cast Request to entity
     *
     * @param employee Request
     * @return Entity Employee
     */
    public static Employee toEntityRegister(RegisterEmployeeINP employee){
        String[] parts = employee.getLastname().trim().split(" ");
        String surname = "";
        String secondSurname = "";
        if(parts.length == 2){
            surname = parts[0];
            secondSurname = parts[1];
        }else {
            surname = employee.getLastname();
        }
        return Employee.builder()
                .id(employee.getId())
                .identification(employee.getIdentification())
                .names(employee.getNames())
                .surname(surname)
                .secondSurname(secondSurname)
                .email(employee.getEmail())
                .status(true)
                .build();
    }
}