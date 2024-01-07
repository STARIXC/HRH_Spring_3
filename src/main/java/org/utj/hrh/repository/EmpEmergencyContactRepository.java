package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmployeeEducation;
import org.utj.hrh.model.EmployeeEmergencyContact;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmpEmergencyContactRepository extends JpaRepository<EmployeeEmergencyContact, Long> {
  Long countById(Long id);

//  @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.employeeContacts WHERE e.person.personNumber = :emp_no")
//  Optional<Employee> findByEmpNoWithContacts(@Param("emp_no") String empNo);
  
  List<EmployeeEmergencyContact> findByEmployeeContact(Employee employee);
}
