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
public interface EmpEmergencyContactRepository extends JpaRepository<EmployeeEmergencyContact, BigDecimal> {
  Long countBySeqNo(BigDecimal id);
  
  // Find econtacts by person number
//  List<EmployeeEmergencyContact> findByEmployeeContact_Person_PersonNumber(String personNumber);
  @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.employeeContacts WHERE e.person.personNumber = :emp_no")
  Optional<Employee> findByEmpNoWithContacts(@Param("emp_no") String empNo);
}
