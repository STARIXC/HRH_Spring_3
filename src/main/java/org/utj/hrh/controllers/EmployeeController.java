package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.dto.*;
import org.utj.hrh.model.*;
import org.utj.hrh.repository.EmployeeRepository;
import org.utj.hrh.repository.PersonRepository;
import org.utj.hrh.services.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Controller
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	private final RoleService roleService;
	private final UserService userService;
	private final EmployeeRepository employeeRepository;
	private final EmployeeStatusService employeeStatusService;
	private final EmployeeService employeeService;
	private final CarderCategoryService carderCategoryService;
	private final CountyService countyService;
	private final DesignationService designationService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final PersonRepository personRepository;
	private final EducationLevelService educationLevelService;
	private final EmpEmergencyContactService empEmergencyContactService;
	private final EmployeeEducationQualificationService employeeEducationQualificationService;
	private final LeavePolicyService leavePolicyService;
	
	@Autowired
	public EmployeeController(RoleService roleService, UserService userService, EmployeeRepository employeeRepository, EmployeeStatusService employeeStatusService, EmployeeService employeeService, CarderCategoryService carderCategoryService, CountyService countyService, DesignationService designationService, BCryptPasswordEncoder passwordEncoder, PersonRepository personRepository, EducationLevelService educationLevelService, EmpEmergencyContactService empEmergencyContactService, EmployeeEducationQualificationService employeeEducationQualificationService, LeavePolicyService leavePolicyService) {
		this.roleService = roleService;
		this.userService = userService;
		this.employeeRepository = employeeRepository;
		this.employeeStatusService = employeeStatusService;
		this.employeeService = employeeService;
		this.carderCategoryService = carderCategoryService;
		this.countyService = countyService;
		this.designationService = designationService;
		this.passwordEncoder = passwordEncoder;
		this.personRepository = personRepository;
		this.educationLevelService = educationLevelService;
		this.empEmergencyContactService = empEmergencyContactService;
		this.employeeEducationQualificationService = employeeEducationQualificationService;
		this.leavePolicyService = leavePolicyService;
	}
	
	
	@GetMapping("/system/employee/all")
	public String getAllEmployees(Model model) {
		try {
			
			model.addAttribute("pageTitle", "STAFF");
			model.addAttribute("httpStatus", HttpStatus.OK);
			List<StaffDTO> employeeDTOs = employeeService.getAll();
			model.addAttribute("employees", employeeDTOs);
//            logger.info("Employees:[]"+employeeDTOs);
			return "pages/admin/employee/employees";
		} catch (NullPointerException e) {
			// Log the error message and stack trace
			logger.error("An error occurred while fetching employees.", e);
			// Handle the exception, e.g., return an error page or message
			model.addAttribute("error", "An error occurred while fetching employees.");
			model.addAttribute("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR);
			return "/error/error-500"; // Create an error page view
		}
	}
	
	@GetMapping("system/employee/form")
	public String showAddNewForm(Model model) {
		// Create operation, set the create flag
		model.addAttribute("createMode", true);
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		List<EmployeeStatus> employeeStatuses = employeeStatusService.getAll();
		model.addAttribute("employeeStatuses", employeeStatuses);
		List<CarderCategory> categories = carderCategoryService.getAll();
		model.addAttribute("categories", categories);
		List<County> counties = countyService.getActive();
		model.addAttribute("counties", counties);
		
		return "pages/admin/employee/add-employee"; // This corresponds to the Thymeleaf template
	}
	

	
	@GetMapping("/count/started-between")
	public Long getCountOfEmployeesStartedBetween() {
		YearMonth currentMonth = YearMonth.now();
		LocalDate startDate = currentMonth.atDay(1);
		LocalDate endDate = currentMonth.atEndOfMonth();
		return employeeRepository.countByDateStartedBetween(startDate, endDate);
	}

	@GetMapping("system/employee/{emp_no}/view")
	public String viewEmployee(@PathVariable(name = "emp_no") Long emp_no, Model model) throws EntityNotFoundException {
		EmployeeDetailsDTO employeeDetails = employeeService.getEmployeeDetails(emp_no);
		logger.info("Employee found: {}", employeeDetails);
		model.addAttribute("employeeDetails", employeeDetails);
		model.addAttribute("id", emp_no);
		model.addAttribute("activeTab", "basic");
		model.addAttribute("pageTitle", "View :: Employee Profile");
		
		return "pages/admin/employee/employee";
	}

	@PostMapping("/system/employee/basic/saveEmployee")
	public String saveBasic(@ModelAttribute Employee employee, Model model) throws EntityNotFoundException {
		Employee saveEmployee = new Employee();
		// Retrieve personNumber from the employee object
		String personNumber = employee.getPerson().getPersonNumber();
		
		// Do something with the personNumber (e.g., save to a database)
		String firstName = employee.getFirstName();
		String surname = employee.getSurname();
		String otherName = employee.getOtherName();
		String email = employee.getEmail();
		
		String fullName = "";
		if (firstName != null && !firstName.isEmpty() && surname != null && !surname.isEmpty() && otherName != null && !otherName.isEmpty()) {
			fullName = firstName + " " + otherName + " " + surname;
		} else {
			fullName = firstName + " " + surname;
		}
		// Create a new Person instance and set name and email
		Person person = new Person();
		person.setName(fullName);
		person.setEmail(email);
		person.setPersonNumber(personNumber);
		
		// Save the Person entity first (assuming you don't have it already)
		Person savedPerson = personRepository.save(person);
		
		// Set the saved Person in the Employee entity
		employee.setPerson(savedPerson);
		
		// Save the Employee entity
		Employee savedEmployee = employeeRepository.save(employee);
		
		// Retrieve the saved personNumber from the saved entities
		String savedPersonNumber = savedEmployee.getPerson().getPersonNumber();
		
		// Add the saved personNumber to the model if you want to use it in the view
		model.addAttribute("personNumber", savedPersonNumber);
		
		
		User user = new User();
		// Create a username based on the rules
		if (employee.isHasLoginAccess()) {
			// Set timestamps and defaults if not provided
			LocalDateTime currentDateTime = LocalDateTime.now();
			if (user.getCreated_at() == null) {
				user.setCreated_at(currentDateTime);
			}
			// Check if first name and surname are not empty
			if (firstName != null && !firstName.isEmpty() && surname != null && !surname.isEmpty()) {
				// Create the username by taking the first letter of the first name and concatenating it with the surname
				String username = firstName.substring(0, 1).toUpperCase() + surname.toUpperCase();
				
				user.setUsername(username);
			}
//            user.setPerson(user.getPerson().setPersonNumber());
			// Ensure that md5PasswordEncoder is not null before using it
			if (passwordEncoder != null) {
				String password = employee.getNationalId(); // Use empNo as the initial password
				String encryptedPassword = passwordEncoder.encode(password); // Password hashing
				user.setPassword(encryptedPassword);
			} else {
				logger.info("Error Encripting  form data: " + employee.getNationalId());
			}
			user.setPerson(savedPerson);
			String role_name = "Employee";
			Role role = roleService.getRoleByName(role_name);
			user.setRole(role);
			user.setStatus(true);
			user.setDeleted(false);
			userService.save(user);
			
			
		}
		String empNumber = employee.getPerson().getPersonNumber();
		System.out.println(employee.toString());
		logger.info("Received form data: " + employee.toString());
		// Redirect to a success page or another appropriate page
		return "redirect:/system/employee/view/" + savedPersonNumber;
		
	}
	
	@PostMapping("/system/employee/addBasic/saveEmployee")
	public String updateBasic(@ModelAttribute Employee employee, Model model) throws EntityNotFoundException {
		new Employee();
		// Retrieve personNumber from the employee object
		String employee_no = employee.getPerson().getPersonNumber();
		// Do something with the personNumber (e.g., save to a database)
		String firstName = employee.getFirstName();
		String surname = employee.getSurname();
		String otherName = employee.getOtherName();
		String email = employee.getEmail();
		
		String fullName = "";
		if (firstName != null && !firstName.isEmpty() && surname != null && !surname.isEmpty() && otherName != null && !otherName.isEmpty()) {
			fullName = firstName + " " + otherName + " " + surname;
		} else {
			fullName = firstName + " " + surname;
		}
		// Create a new Person instance and set name and email
		Person person = new Person();
		person.setName(fullName);
		person.setEmail(email);
		person.setPersonNumber(employee_no);
		
		// Save the Person entity first (assuming you don't have it already)
		Person savedPerson = personRepository.save(person);
		
		// Set the saved Person in the Employee entity
		employee.setPerson(savedPerson);
		
		// Save the Employee entity
		Employee savedEmployee = employeeRepository.save(employee);
		
		// Retrieve the saved personNumber from the saved entities
		String savedPersonNumber = savedEmployee.getPerson().getPersonNumber();
		
		// Add the saved personNumber to the model if you want to use it in the view
		model.addAttribute("personNumber", savedPersonNumber);
		
		
		User user = new User();
		// Create a username based on the rules
		if (employee.isHasLoginAccess()) {
			// Set timestamps and defaults if not provided
			LocalDateTime currentDateTime = LocalDateTime.now();
			if (user.getCreated_at() == null) {
				user.setCreated_at(currentDateTime);
			}
			// Check if first name and surname are not empty
			if (firstName != null && !firstName.isEmpty() && surname != null && !surname.isEmpty()) {
				// Create the username by taking the first letter of the first name and concatenating it with the surname
				String username = firstName.substring(0, 1).toUpperCase() + surname.toUpperCase();
				
				user.setUsername(username);
			}
//            user.setPerson(user.getPerson().setPersonNumber());
			// Ensure that md5PasswordEncoder is not null before using it
			if (passwordEncoder != null) {
				String password = employee.getNationalId(); // Use empNo as the initial password
				String encryptedPassword = passwordEncoder.encode(password); // Password hashing
				user.setPassword(encryptedPassword);
			} else {
				logger.info("Error Encripting  form data: " + employee.getNationalId());
			}
			user.setPerson(savedPerson);
			String role_name = "Employee";
			Role role = roleService.getRoleByName(role_name);
			user.setRole(role);
			user.setStatus(true);
			user.setDeleted(false);
			userService.save(user);
			
			
		}
		String empNumber = employee.getPerson().getPersonNumber();
		System.out.println(employee.toString());
		logger.info("Received form data: " + employee.toString());
		// Redirect to a success page or another appropriate page
		return "redirect:/system/employee/view/" + savedPersonNumber;
		
	}
	@PostMapping("/system/employee/update-basic-info/{employeeId}")
	@ResponseBody
	public ResponseEntity<?> updateBasicInfo(@PathVariable Long employeeId,
	                                           @RequestBody BasicInfoDTO basicInfoDTO) throws EntityNotFoundException {
		logger.info("Updating contact info for employee {}", basicInfoDTO);
		// Additional debug logging
		logger.info("First Name: {}", basicInfoDTO.getFirstName());
		employeeService.updateBasicInfo(employeeId, basicInfoDTO);
		return ResponseEntity.ok("Employee contact info updated successfully");
	}
	
	@PostMapping("/system/employee/update-statutory-info/{employeeId}")
	@ResponseBody
	public ResponseEntity<?> updateStatutoryInfo(@PathVariable Long employeeId,
	                                         @RequestBody StatutoryDetailsDTO statutoryDetailsDTO) throws EntityNotFoundException {
		logger.info("Updating statutory info for employee {}", statutoryDetailsDTO);
		// Additional debug logging
		employeeService.updateStatutoryInfo(employeeId, statutoryDetailsDTO);
		return ResponseEntity.ok("Employee statutory info updated successfully");
	}
@PostMapping("/system/employee/update-contact-info/{employeeId}")
@ResponseBody
public ResponseEntity<?> updateContactInfo(@PathVariable Long employeeId,
                                           @RequestBody ContactInfoDTO contactInfoDTO) throws EntityNotFoundException {
	logger.info("Updating contact info for employee {}", contactInfoDTO);
	// Additional debug logging
	logger.info("Phone: {}", contactInfoDTO.getPhone());
	logger.info("Email: {}", contactInfoDTO.getEmail());
	logger.info("Present Address: {}", contactInfoDTO.getPresentAddress());
	logger.info("Home Address: {}", contactInfoDTO.getHomeAddress());
	employeeService.updateContactInfo(employeeId, contactInfoDTO);
	return ResponseEntity.ok("Employee contact info updated successfully");
}
@PostMapping("/system/employee/update-emergency-contact-info/{employeeId}")
@ResponseBody
public ResponseEntity<?> updateEmergencyContactInfo(@PathVariable Long employeeId,
                                           @RequestBody EmployeeEmergencyContactDTO empEmergencyContact) throws EntityNotFoundException {
	logger.info("Updating contact info for employee {}", empEmergencyContact);
	
	empEmergencyContactService.updateEmergencyContactInfo(employeeId, empEmergencyContact);
	return ResponseEntity.ok("Employee contact info updated successfully");
}

}