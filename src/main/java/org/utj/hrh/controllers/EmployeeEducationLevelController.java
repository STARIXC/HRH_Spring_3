package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.dto.EmployeeAcademicQualificationDTO;
import org.utj.hrh.services.*;

@Controller
@RequestMapping("/system/employee/employee-education")
public class EmployeeEducationLevelController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeEducationLevelController.class);
	
	private final EducationLevelService educationLevelService;
	private final EmployeeService employeeService;
	@Autowired
	public EmployeeEducationLevelController( EducationLevelService educationLevelService, EmployeeService employeeService) {
	
		this.educationLevelService = educationLevelService;
		this.employeeService = employeeService;
	}

}
