package com.wilsoncajisaca.vacunacion.controllers;

import com.wilsoncajisaca.vacunacion.commons.Commons;
import com.wilsoncajisaca.vacunacion.entities.Employee;
import com.wilsoncajisaca.vacunacion.exception.GeneralException;
import com.wilsoncajisaca.vacunacion.pojos.UpdateEmployeeINP;
import com.wilsoncajisaca.vacunacion.pojos.errors.ApiError;
import com.wilsoncajisaca.vacunacion.pojos.RegisterEmployeeINP;
import com.wilsoncajisaca.vacunacion.security.JwtTokenProvider;
import com.wilsoncajisaca.vacunacion.security.UserInfoAuthentication;
import com.wilsoncajisaca.vacunacion.service.EmployeeService;
import com.wilsoncajisaca.vacunacion.utils.Validations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/employee/")
@SecurityRequirement(name = "Token")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> test() {
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("response", "OK");
        return ResponseEntity.ok().body(resp);
    }

    /**
     * {@code POST  /create} : Create a employee
     * Save data of a employee
     *
     * @param employeeINP Data Employee for save
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body of new employee
     */
    @Operation(summary = "Permite crear un nuevo empleado")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@Valid @RequestBody RegisterEmployeeINP employeeINP, Errors errors) throws Exception {
        Commons.validateFieldRequest(errors);
        log.debug("REST request to create an employee : {}", employeeINP);
        Map<String,Object> employee = employeeService.createEmployee(employeeINP);
        return ResponseEntity.ok().body(employee);
    }

    /**
     * {@code PUT  /update} : update an employee
     * Update data of an employee
     *
     * @param employeeINP Data Employee for update
     * @return the {@link ResponseEntity} with status {@code 202 (OK)} and with body of an employee
     */
    @Operation(summary = "Permite actualizar un empleado por el administrador")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateEmployee(@Valid @RequestBody RegisterEmployeeINP employeeINP, Errors errors) throws Exception {
        Commons.validateFieldRequest(errors);
        if(employeeINP.getId() == null || employeeINP.getId().toString().isEmpty()){
            throw new GeneralException(new ApiError("No se encontro el id del empleado"));
        }
        log.debug("REST request to update an employee : {}", employeeINP);
        Employee employee = employeeService.updateEmployee(employeeINP);
        return ResponseEntity.ok().body(employee);
    }

    /**
     * {@code GET  /getAll} : get all employees
     * @return the {@link ResponseEntity} with status {@code 202 (OK)} and with body of an employee
     */
    @Operation(summary = "Lista todos los empleados")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllEmployee() throws Exception {
        log.debug("REST request to list all employee");
        Set<Employee> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok().body(employees);
    }

    @Operation(summary = "Elimina un empleado por su id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteEmployee(@RequestParam("employee") Long employeeId) throws Exception {
        log.debug("REST request to list all employee");
        if(employeeId == null || employeeId.toString().isEmpty()){
            throw new GeneralException(new ApiError("No se encontro el id del empleado"));
        }
        Map<String, Object> response = employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Permite visualizar la informacion del empleado")
    @GetMapping(value = "get-complete-information", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getInfoByEmployee() throws Exception {
        log.debug("REST request get info of an getInfoByEmployee");
        Employee employee = employeeService.getInfoByEmployee();
        return ResponseEntity.ok().body(employee);
    }

    /**
     * {@code PUT  /update} : update an employee
     * Update data of an employee
     *
     * @param employeeINP Data Employee for update
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body of an employee
     */
    @Operation(summary = "Permite actualizar la informacion del empleado por el mismo empleado")
    @PutMapping(value = "update-complete-information", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCompleteEmployee(@Valid @RequestBody UpdateEmployeeINP employeeINP, Errors errors) throws Exception {
        employeeINP.setUsername(UserInfoAuthentication.getUsername());
        Commons.validateFieldRequest(errors);
        log.debug("REST request to update an employee : {}", employeeINP);
        Employee employee = employeeService.updateEmployee(employeeINP);
        return ResponseEntity.ok().body(employee);
    }

    @Operation(summary = "Obtiene la lista de empleados por su estado de vacunacion")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "get-employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEmployee(@RequestParam("vacunado") Boolean vaccinationStatus) throws Exception {
        log.debug("REST request to list All by status");
        Set<Employee> employees = employeeService.getAllEmployeeByStatusVaccination(vaccinationStatus);
        return ResponseEntity.ok().body(employees);
    }

    @Operation(summary = "Obtiene la lista de empleados por su tipo de vacuna")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "get-employee-type-vaccinate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEmployeeByVaccinate(@RequestParam("tipo_vacuna") Long vaccinationType) throws Exception {
        log.debug("REST request to list All by status");
        Set<Employee> employees = employeeService.getAllEmployeeByTypeVaccination(vaccinationType);
        return ResponseEntity.ok().body(employees);
    }

    @Operation(summary = "Obtiene la lista de empleados por el rango de fechas de su vacunacion")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "get-employee-date-vaccinate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEmployeeByDate(@RequestParam("fecha_inicio") String fromDateStr,
                                                    @RequestParam("fecha_fin") String toDateStr) throws Exception {
        log.debug("REST request to list All by status");
        Date fromDate = Validations.parseDate(fromDateStr);
        Date toDate = Validations.parseDate(toDateStr);
        Set<Employee> employees = employeeService.getEmployeeByVaccinateDate(fromDate, toDate);
        return ResponseEntity.ok().body(employees);
    }
}
