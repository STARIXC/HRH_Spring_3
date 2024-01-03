package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.EmployeeEducation;

import java.util.List;

@Repository
public interface EmployeeEducationRepository extends JpaRepository<EmployeeEducation,Long> {
  Long countById(Long id);
  
  // Find academic qualifications by person number
  List<EmployeeEducation> findByAcademicQualification_Person_PersonNumber(String personNumber);

}
