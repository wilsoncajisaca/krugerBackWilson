package com.wilsoncajisaca.vacunacion.mappers;

import com.wilsoncajisaca.vacunacion.entities.Address;
import com.wilsoncajisaca.vacunacion.entities.VaccinationEmployee;
import com.wilsoncajisaca.vacunacion.pojos.UpdateEmployeeINP;

/**
 * Mapping entity employee VaccinationEmployee
 */
public class EmployeeVaccinationMapper {
    /**
     * Cast Request to entity
     *
     * @param vaccination Request
     * @return Entity Employee VaccinationEmployee
     */
    public static VaccinationEmployee toEntity(UpdateEmployeeINP.VaccinationEmployee vaccination){
        return VaccinationEmployee.builder()
                .id(vaccination.getVaccinationId())
                .idEmployee(vaccination.getEmployee())
                .idVaccinationType(vaccination.getVaccineType())
                .vaccinationDate(vaccination.getVaccinationDate())
                .numberDoses(vaccination.getNumberDoses())
                .build();
    }
}
