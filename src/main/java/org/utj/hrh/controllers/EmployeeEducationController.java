package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.dto.EmployeeEducationDTO;
import org.utj.hrh.model.EducationLevel;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmployeeEducation;
import org.utj.hrh.services.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/system/employee/employee-education")
public class EmployeeEducationController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeEducationController.class);
	private final EmployeeEducationService service;
	private final EducationService educationService;
	private final EmployeeService employeeService;
	@Autowired
	public EmployeeEducationController(EmployeeEducationService service, EducationService educationService, EmployeeService employeeService) {
		this.service = service;
		this.educationService = educationService;
		this.employeeService = employeeService;
	}
	
//	@PostMapping("/save/qualification")
//	public String saveAcademicQualification(@ModelAttribute EmployeeEducation employeeEducation, Model model) throws EntityNotFoundException, EmployeeNotFoundException {
//		System.out.println(employeeEducation.toString());
//		// Check if the Employee object (academicQualification) is already persisted
//
//		Employee persistedEmployee = employeeService.getEmployee(employeeEducation.getAcademicQualification().getPerson().getPersonNumber());
//		if (persistedEmployee == null) {
//			// Handle the case where the Employee is not found
//			// e.g., redirect to an error page or show an error message
//		}
//		Education educationId=educationService.findEducationById(employeeEducation.getEducation().getId());
//
//		Employee EmployeeNumber = persistedEmployee;
//		Education education_id=educationId;
//		String institution=employeeEducation.getInstitute();
//		String major=employeeEducation.getMajor();
//		Integer year=employeeEducation.getYear();
//		String score=employeeEducation.getScore();
//		LocalDate startDate=employeeEducation.getStartDate();
//		LocalDate endDate=employeeEducation.getEndDate();
//
//		EmployeeEducation saveEmployee= new EmployeeEducation();
////		saveEmployee.setAcademicQualification(EmployeeNumber);
//		saveEmployee.setEducation(education_id);
//		saveEmployee.setInstitute(institution);
//		saveEmployee.setMajor(major);
//		saveEmployee.setYear(year);
//		saveEmployee.setScore(score);
//		saveEmployee.setStartDate(startDate);
//		saveEmployee.setEndDate(endDate);
//
//		System.out.println(saveEmployee.toString());
//		service.save(saveEmployee);
//		logger.info("Received form data: " + saveEmployee.toString());
//		// Redirect to a success page or another appropriate page
//        return "redirect:/system/employee/view/" + employeeEducation.getAcademicQualification().getPerson().getPersonNumber();
////		return null;
//	}
//
@PostMapping("/save/qualification")

public String saveEmployeeEducation(@ModelAttribute  EmployeeEducationDTO employeeEducationDTO) {
	// Process the DTO, e.g., save to database
	service.saveEmployeeEducation(employeeEducationDTO);
	return "Success"; // You can return a more meaningful response or object
}

}
