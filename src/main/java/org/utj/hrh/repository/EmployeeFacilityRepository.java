package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmployeeFacility;
import org.utj.hrh.model.Facility;

import java.util.List;

public interface EmployeeFacilityRepository extends JpaRepository<EmployeeFacility, Long> {


    EmployeeFacility findByEmployee(Employee employee);
}
