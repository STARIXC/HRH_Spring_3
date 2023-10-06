package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByStatus(int isActive);

    @Query("SELECT COUNT(e) FROM Employee e WHERE FUNCTION('STR_TO_DATE',e.dateStarted, '%Y-%m-%d') BETWEEN :startDate AND :endDate")
    Long countByDateStartedBetween(@Param("startDate") LocalDate startOfMonth, @Param("endDate") LocalDate endOfMonth);


     Optional<Employee> findEmployeeById(String  id) ;
    @Query("SELECT e from Employee e where e.empNo =:emp_no")
    public Employee getEmployeeByEmp_no(@Param("emp_no") String emp_no);

//     long countById(Integer id);

    @Query("Update Employee e set e.status=?2 where e.empNo= ?1")
    @Modifying
    public void updateEnabledStatus(String id, boolean enabled);



}
