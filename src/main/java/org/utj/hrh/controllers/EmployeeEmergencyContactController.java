package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.utj.hrh.dto.EmployeeEmergencyContactDTO;
import org.utj.hrh.services.EmpEmergencyContactService;
import org.utj.hrh.services.EntityNotFoundException;

@Controller
@RequestMapping("/system/employee/emergencyContact")
public class EmployeeEmergencyContactController {
	
	private final EmpEmergencyContactService emergencyContactService;
	
	@Autowired
	public EmployeeEmergencyContactController(EmpEmergencyContactService emergencyContactService) {
		this.emergencyContactService = emergencyContactService;
	}
	@GetMapping("/find/{id}")
	@ResponseBody
	public ResponseEntity<EmployeeEmergencyContactDTO> getEmergencyContact(@PathVariable Long id) {
		try {
			EmployeeEmergencyContactDTO emergencyContactDTO = emergencyContactService.getEmergencyContactById(id);
			return ResponseEntity.ok(emergencyContactDTO);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
