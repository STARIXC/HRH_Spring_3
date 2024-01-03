package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmployeeFacility;
import org.utj.hrh.model.Facility;

import java.util.List;
import java.util.Optional;

public interface EmployeeFacilityRepository extends JpaRepository<EmployeeFacility, Long> {
	
	Optional<EmployeeFacility> findByEmployee_Person_PersonNumber(String personNumber);
//    EmployeeFacility findByEmployee(Employee employee);
}
