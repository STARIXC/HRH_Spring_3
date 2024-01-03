package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.utj.hrh.dto.StaffDTO;
import org.utj.hrh.model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT COUNT(e) FROM Employee e WHERE FUNCTION('STR_TO_DATE',e.dateStarted, '%Y-%m-%d') BETWEEN :startDate AND :endDate")
    Long countByDateStartedBetween(@Param("startDate") LocalDate startOfMonth, @Param("endDate") LocalDate endOfMonth);

     Employee findByPersonPersonNumber(String  id) ;
    @Query("SELECT e from Employee e where e.person.personNumber =:emp_no")
    Employee getEmployeeByEmp_no(@Param("emp_no") String emp_no);

    Long countById(Long id);
    
    List<Employee> findByGender(String gender);
//    List<StaffDTO> findByGender(String gender);
    
    @Query("SELECT e.phone, e.email, e.altPhone, e.altEmail, e.presentAddress, e.homeAddress FROM Employee e WHERE e.person.personNumber = :employeeId")
    Object[] findEmployeeContactDetailsById(Long employeeId);


}
