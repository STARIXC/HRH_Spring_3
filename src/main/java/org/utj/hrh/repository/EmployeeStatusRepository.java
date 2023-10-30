package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.EmployeeStatus;
import org.utj.hrh.model.Facility;

import java.util.List;

@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus,Integer> {


}
