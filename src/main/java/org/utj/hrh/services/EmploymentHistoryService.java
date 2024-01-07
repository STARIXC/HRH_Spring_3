package org.utj.hrh.services;

import org.springframework.stereotype.Service;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmploymentHistory;
import org.utj.hrh.repository.EmploymentHistoryRepository;

@Service
public class EmploymentHistoryService {
	private final EmploymentHistoryRepository employmentHistoryRepository;
	
	public EmploymentHistoryService(EmploymentHistoryRepository employmentHistoryRepository) {
		this.employmentHistoryRepository = employmentHistoryRepository;
	}
	
	public EmploymentHistory findActiveEmploymentHistoryForEmployee(Employee source) {
		return employmentHistoryRepository.findByEmployeeHistoryRecordAndIsActive(source, true)
				.orElse(null);
	}
}
