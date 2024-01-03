package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.utj.hrh.dto.EmployeeEducationDTO;
import org.utj.hrh.dto.EmployeeEmergencyContactDTO;
import org.utj.hrh.helper.AjaxResponse;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmployeeEducation;
import org.utj.hrh.model.EmployeeEmergencyContact;
import org.utj.hrh.services.EmpEmergencyContactService;
import org.utj.hrh.services.EmployeeNotFoundException;
import org.utj.hrh.services.EmployeeService;
import org.utj.hrh.services.EntityNotFoundException;


import java.time.LocalDate;
@Controller
public class EmpEmergencyContactController {
	private static final Logger logger = LoggerFactory.getLogger(EmpEmergencyContactController.class);
	
	private final EmpEmergencyContactService service;
	private final EmployeeService employeeService;
	@Autowired
	public EmpEmergencyContactController(EmpEmergencyContactService service, EmployeeService employeeService) {
		this.service = service;
		this.employeeService = employeeService;
	}
	
	
	@PostMapping("/system/employee/save/emergency-contact")
	public String saveEmployeeEmergencyContact(@ModelAttribute EmployeeEmergencyContact employeeEmergencyContact, Model model) throws EntityNotFoundException, EmployeeNotFoundException {
//		System.out.println(employeeEmergencyContact.toString());
//		// Check if the Employee object (academicQualification) is already persisted
//
//		Employee persistedEmployee = employeeService.getEmployee(employeeEmergencyContact.getEmployeeContact().getPerson().getPersonNumber());
//		if (persistedEmployee == null) {
//			// Handle the case where the Employee is not found
//			// e.g., redirect to an error page or show an error message
//		}
//
//		Employee EmployeeNumber = persistedEmployee;
//		String name=employeeEmergencyContact.getName();
//		String relationship=employeeEmergencyContact.getRelationship();
//		String homePhone=employeeEmergencyContact.getHomePhone();
//		String mobilePhone=employeeEmergencyContact.getMobilePhone();
//		String officePhone=employeeEmergencyContact.getOfficePhone();
//
//
//		EmployeeEmergencyContact saveEmployee= new EmployeeEmergencyContact();
//		saveEmployee.setEmployeeContact(EmployeeNumber);
//		saveEmployee.setName(name);
//		saveEmployee.setRelationship(relationship);
//		saveEmployee.setHomePhone(homePhone);
//		saveEmployee.setMobilePhone(mobilePhone);
//		saveEmployee.setOfficePhone(officePhone);
//
//
//		System.out.println(saveEmployee.toString());
//		service.save(saveEmployee);
//		logger.info("Received form data: " + saveEmployee.toString());
//		// Redirect to a success page or another appropriate page
//		return "redirect:/system/employee/view/" + employeeEmergencyContact.getEmployeeContact().getPerson().getPersonNumber();
//
		return null;
	}
	@PostMapping("/system/employee/save/emergency/contact")
	@ResponseBody
	public ResponseEntity<AjaxResponse> saveEmergencyContactRecord(
			@ModelAttribute EmployeeEmergencyContactDTO employeeEmergencyContactDTO) {
		
		AjaxResponse response = new AjaxResponse();
		try {
			// Process the DTO, e.g., save to database
			service.saveEmployeeEmergencyContact(employeeEmergencyContactDTO);
			
			// Set success response
			response.setSuccess(true);
			response.setMessage("Emergency contact saved successfully");
			response.setData(null); // You can add any data you want to return
		} catch (Exception e) {
			// Handle exceptions
			response.setSuccess(false);
			response.setMessage("Error saving emergency contact");
			// Optionally log the exception or set error details
		}
		
		return ResponseEntity.ok(response);
	}
}
