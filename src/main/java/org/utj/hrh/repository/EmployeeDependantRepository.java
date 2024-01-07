package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Dependant;
import org.utj.hrh.model.Employee;

import java.util.List;


@Repository
public interface EmployeeDependantRepository extends JpaRepository<Dependant,Long> {
	
	List<Dependant> findByEmployeeDependant(Employee employee);
}
