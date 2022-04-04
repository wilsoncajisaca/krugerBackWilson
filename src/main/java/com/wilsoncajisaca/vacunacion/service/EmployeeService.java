package com.wilsoncajisaca.vacunacion.service;

import com.wilsoncajisaca.vacunacion.entities.Employee;
import com.wilsoncajisaca.vacunacion.exception.GeneralException;
import com.wilsoncajisaca.vacunacion.pojos.RegisterEmployeeINP;
import com.wilsoncajisaca.vacunacion.pojos.UpdateEmployeeINP;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Service interface for managing
 * {@link com.wilsoncajisaca.vacunacion.entities.Employee}
 */
public interface EmployeeService {

    /**
     * Save a new employee
     *
     * @param employee the entity data to save
     * @return the persisted entity
     */
    Map<String, Object> createEmployee(RegisterEmployeeINP employee) throws GeneralException;

    /**
     * update an employee
     *
     * @param employee the entity data to update
     * @return the persisted entity
     */
    Employee updateEmployee(RegisterEmployeeINP employee) throws GeneralException;

    /**
     * Get all employees
     * @return the persisted entity
     */
    Set<Employee> getAllEmployee() throws GeneralException;

    /**
     * Delete employee by id
     * @param employeeId id for delete an entity
     * @return a information map
     */
    Map<String,Object> deleteEmployee(Long employeeId) throws GeneralException;


    /**
     * update an employee with complete information
     *
     * @param employee the entity data to update
     * @return the persisted entity
     */
    Employee updateEmployee(UpdateEmployeeINP employee) throws GeneralException, Exception;

    Set<Employee> getAllEmployeeByStatusVaccination(Boolean statusVaccinated) throws GeneralException;

    Set<Employee> getAllEmployeeByTypeVaccination(Long vaccinateType) throws GeneralException;

    Set<Employee> getEmployeeByVaccinateDate(Date fromDate, Date toDate) throws GeneralException;

    Employee getInfoByEmployee(String username) throws GeneralException;
}
