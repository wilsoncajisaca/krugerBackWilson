package com.wilsoncajisaca.vacunacion.service.impl;

import com.wilsoncajisaca.vacunacion.commons.Commons;
import com.wilsoncajisaca.vacunacion.entities.Auth;
import com.wilsoncajisaca.vacunacion.entities.Employee;
import com.wilsoncajisaca.vacunacion.entities.Role;
import com.wilsoncajisaca.vacunacion.entities.enums.VaccinationStatus;
import com.wilsoncajisaca.vacunacion.exception.GeneralException;
import com.wilsoncajisaca.vacunacion.mappers.AuthMapper;
import com.wilsoncajisaca.vacunacion.mappers.EmployeeMapper;
import com.wilsoncajisaca.vacunacion.pojos.RegisterEmployeeINP;
import com.wilsoncajisaca.vacunacion.pojos.UpdateEmployeeINP;
import com.wilsoncajisaca.vacunacion.repositories.AuthRepository;
import com.wilsoncajisaca.vacunacion.repositories.EmployeeRepository;
import com.wilsoncajisaca.vacunacion.repositories.RolesRepository;
import com.wilsoncajisaca.vacunacion.service.EmployeeService;
import com.wilsoncajisaca.vacunacion.service.tools.EmployeeTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service implementation for managing {@link Employee}
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends EmployeeTool implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    @Transactional
    public Map<String, Object> createEmployee(RegisterEmployeeINP employee) throws GeneralException {
        log.debug("Service to save an employee : {}", employee);
        Map<String,Object> resp = new LinkedHashMap<>();
        Commons.validateIdentifier(employee.getIdentification());
        existEmployeeByIdentification(employee.getIdentification());
        Employee employeeEntity = EmployeeMapper.toEntityRegister(employee);
        Employee emp = employeeRepository.save(employeeEntity);
        resp.put("username", emp.getIdentification());
        resp.put("password", createAuthPassword(emp));
        resp.put("employee", emp);
        return resp;
    }

    private String createAuthPassword(Employee employee){
        String password = Commons.createAleatoryPassword();
        Auth auth = AuthMapper.toEntity(employee.getId(), employee.getIdentification(), passwordEncoder.encode(password));
        Role role = rolesRepository.findByName("ROLE_USER").orElseThrow(this::generateErrorNotFoundRole);
        auth.setRoles(Collections.singleton(role));
        authRepository.save(auth);
        return password;
    }

    @Override
    @Transactional
    public Employee updateEmployee(RegisterEmployeeINP employee) throws GeneralException {
        log.debug("Service to update an employee : {}", employee);
        Commons.validateIdentifier(employee.getIdentification());
        Employee employeeEntity = updateDataEmployee(employee);
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public Set<Employee> getAllEmployee() throws GeneralException {
        return employeeRepository.findAllByOrderBySurnameAsc();
    }

    @Override
    @Transactional
    public Map<String, Object> deleteEmployee(Long employeeId) throws GeneralException {
        Map<String,Object> response = new LinkedHashMap<>();
        getEmployeeById(employeeId);
        employeeRepository.deleteById(employeeId);
        response.put("response", "ok");
        return response;
    }

    @Override
    @Transactional
    public Employee updateEmployee(UpdateEmployeeINP employee) throws Exception {
        log.debug("Service to update an employee with complete information : {}", employee);
        return employeeRepository.save( updateCompleteDataEmployee(employee) );
    }

    @Override
    public Set<Employee> getAllEmployeeByStatusVaccination(Boolean statusVaccinated) throws GeneralException {
        return employeeRepository.findByVaccinationStatusOrderBySurnameAsc( statusVaccinated ? VaccinationStatus.Y : VaccinationStatus.N);
    }

    @Override
    public Set<Employee> getAllEmployeeByTypeVaccination(Long vaccinateType) throws GeneralException {
        return employeeRepository.getByVaccinateType(vaccinateType);
    }

    @Override
    public Set<Employee> getEmployeeByVaccinateDate(Date fromDate, Date toDate) throws GeneralException {
        return employeeRepository.getByRangeDate(fromDate, toDate);
    }

    @Override
    public Employee getInfoByEmployee(String username) throws GeneralException {
        return employeeRepository.findByIdentificationAndStatus(username,true).orElseThrow(this::generateErrorNotFoundEmployee);
    }

    /**
     * Search and Validate employee into database for identifier
     *
     * @param identification           Identifier
     */
    private void existEmployeeByIdentification(String identification) {
        employeeRepository.findByIdentificationAndStatus(identification, true)
                .stream().findAny().ifPresent(s -> {
                    throw generateErrorEmployee("El empleado ya se encuentra registrado.");
                });
    }

    private Employee updateDataEmployee (RegisterEmployeeINP employeeINP) {
        String[] parts = employeeINP.getLastname().trim().split(" ");
        String surname = "";
        String secondSurname = "";
        if(parts.length == 2){
            surname = parts[0];
            secondSurname = parts[1];
        }else {
            surname = employeeINP.getLastname();
        }
        Employee employee = getEmployeeById(employeeINP.getId());
        employee.setIdentification(employeeINP.getIdentification());
        employee.setNames(employeeINP.getNames());
        employee.setSurname(surname);
        employee.setSecondSurname(secondSurname);
        employee.setEmail(employeeINP.getEmail());
        return employee;
    }

    private Employee updateCompleteDataEmployee (UpdateEmployeeINP employeeINP) {
        Employee employee = getEmployeeById(employeeINP.getEmployeeId());
        employee.setBirthDate(employeeINP.getBirthDate());
        employee.setVaccinationStatus(employeeINP.getVaccinationStatus() ? VaccinationStatus.Y : VaccinationStatus.N);
        employee.setMobilePhones(createEmployeePhone(employee, employeeINP));
        employee.setAddresses(createEmployeeAddress(employee, employeeINP));

        if(employeeINP.getVaccinationStatus()){
            employee.setVaccinationEmployees(createEmployeeVaccination(employee, employeeINP));
        }

        return employee;
    }

    private Employee getEmployeeById(Long employeeId){
        return employeeRepository.findByIdAndStatus(employeeId,true)
                .orElseThrow(this::generateErrorNotFoundEmployee);
    }



}
