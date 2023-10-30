package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.dto.EmployeeDTO;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
//    List<Employee> findByStatus(int isActive);

    @Query("SELECT COUNT(e) FROM Employee e WHERE FUNCTION('STR_TO_DATE',e.dateStarted, '%Y-%m-%d') BETWEEN :startDate AND :endDate")
    Long countByDateStartedBetween(@Param("startDate") LocalDate startOfMonth, @Param("endDate") LocalDate endOfMonth);



//
//    @Query("SELECT e,pe,ep,sc,ct,ef,f FROM Employee e " +
//            "LEFT JOIN PositionEmployee pe ON e.id = pe.emp_no " +
//            "LEFT JOIN EmployeePosition ep ON pe.id = pe.position_id " +
//            "LEFT JOIN StandardCarder sc ON ep.standardized_cadre_id = sc.id " +
//            "LEFT JOIN CarderType ct ON sc.carder_type_id = ct.id " +
//            "LEFT JOIN EmployeeFacility ef ON e.id= ef.emp_no " +
//            "LEFT JOIN Facility f ON ef.centre_sante_id= f.CentreSanteId "  +
//            "ORDER BY e.id ASC")
//    public List<EmployeeDTO> listAllEmployees();



     Optional<Employee> findEmployeeById(String  id) ;
    @Query("SELECT e from Employee e where e.empNo =:emp_no")
    public Employee getEmployeeByEmp_no(@Param("emp_no") String emp_no);

    Long countByEmpNo(String id);

//     long countById(Integer id);

//    @Query("Update Employee e set e.status=?2 where e.empNo= ?1")
//    @Modifying
//    public void updateEnabledStatus(String id, boolean enabled);



}
