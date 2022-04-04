package com.wilsoncajisaca.vacunacion.repositories;

import com.wilsoncajisaca.vacunacion.entities.Employee;
import com.wilsoncajisaca.vacunacion.entities.enums.VaccinationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
/**
 * Spring data repository for the {@link com.wilsoncajisaca.vacunacion.entities.Employee} entity
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Get employee by identification
     *
     * @param identification Owner employee identification
     * @return {@link Employee}
     */
    Optional<Employee> findByIdentificationAndStatus(String identification, Boolean status);

    /**
     * Get Employee by id
     *
     * @param id Owner Employee id
     * @return Employee
     */
    Optional<Employee> findByIdAndStatus(Long id, Boolean status);

    /**
     * Get Employee by id
     *
     * @return Employee
     */
    Set<Employee> findAllByOrderBySurnameAsc();

    Set<Employee> findByVaccinationStatusOrderBySurnameAsc(VaccinationStatus vaccinationStatus);

    @Query("SELECT ve.employee FROM VaccinationEmployee ve" +
            "   WHERE ve.idVaccinationType = :VACCINATE_ID")
    Set<Employee> getByVaccinateType(@Param("VACCINATE_ID") Long vaccinateId);

    @Query("SELECT ve.employee FROM VaccinationEmployee ve" +
            "   WHERE ve.vaccinationDate BETWEEN :FROM_DATE AND :TO_DATE")
    Set<Employee> getByRangeDate(@Param("FROM_DATE") Date fromDate, @Param("TO_DATE") Date toDate);
}
