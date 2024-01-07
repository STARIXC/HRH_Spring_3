package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmploymentHistory;

import java.util.Optional;

@Repository
public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, String> {

	
	Optional<EmploymentHistory> findByEmployeeHistoryRecordAndIsActive(Employee source, boolean b);
	
	Optional<EmploymentHistory> findByEmprecordid(String emprecordid);
}
