package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmployeeFacility;
import org.utj.hrh.model.Facility;

import java.util.List;
import java.util.Optional;

public interface EmployeeFacilityRepository extends JpaRepository<EmployeeFacility, Long> {
	@Query("SELECT ef FROM EmployeeFacility ef WHERE ef.activeEmployeeFacility.id = :employeeId")
	Optional<EmployeeFacility> findActiveFacilityByEmployeeId(@Param("employeeId") Integer employeeId);
	Optional<EmployeeFacility> findByActiveEmployeeFacilityAndIsActive(Employee employee, boolean isActive);

}
