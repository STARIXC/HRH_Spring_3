package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.FacilityDTO;
import org.utj.hrh.model.*;
import org.utj.hrh.repository.EmployeeFacilityRepository;
@Service
public class EmployeeFacilityService {
	
	private final EmployeeFacilityRepository employeeFacilityRepository;
	
	@Autowired
	public EmployeeFacilityService(EmployeeFacilityRepository employeeFacilityRepository) {
		this.employeeFacilityRepository = employeeFacilityRepository;
	}
	
	
	public EmployeeFacility findActiveFacilityForEmployee(Employee employee) {
		return employeeFacilityRepository.findByActiveEmployeeFacilityAndIsActive(employee, true)
				.orElse(null); // Return null or handle it as per your business logic
	}
	
	
	
}
