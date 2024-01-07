package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.utj.hrh.dto.DependantDTO;
import org.utj.hrh.dto.EmployeeEmergencyContactDTO;
import org.utj.hrh.model.Dependant;
import org.utj.hrh.services.EmployeeDependantService;
import org.utj.hrh.services.EntityNotFoundException;

@Controller
@RequestMapping("/system/employee/employee-dependant")
public class EmployeeDependantController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDependantController.class);
	
	private final EmployeeDependantService employeeDependantService;
	
	@Autowired
	public EmployeeDependantController(EmployeeDependantService employeeDependantService) {
				this.employeeDependantService = employeeDependantService;
	}
	
	@GetMapping("/find/{id}")
	@ResponseBody
	public ResponseEntity<DependantDTO> getEmployeeDependant(@PathVariable Long id) {
		try {
			DependantDTO dependantDTO = employeeDependantService.getDependantById(id);
			return ResponseEntity.ok(dependantDTO);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping("/update-dependant-info/{employeeId}")
	@ResponseBody
	public ResponseEntity<?> updateDependantInfo(@PathVariable Long employeeId,
	                                                    @RequestBody DependantDTO dependantDTO) throws EntityNotFoundException {
		logger.info("Updating contact info for employee {}", dependantDTO);
		
		employeeDependantService.updateDependantRecord(employeeId, dependantDTO);
		return ResponseEntity.ok("Employee contact info updated successfully");
	}
}
