package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.utj.hrh.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByStatus(int isActive);

    @Query("SELECT COUNT(e) FROM Employee e WHERE FUNCTION('STR_TO_DATE',e.dateStarted, '%Y-%m-%d') BETWEEN :startDate AND :endDate")
    Long countByDateStartedBetween(@Param("startDate") LocalDate startOfMonth, @Param("endDate") LocalDate endOfMonth);


}
