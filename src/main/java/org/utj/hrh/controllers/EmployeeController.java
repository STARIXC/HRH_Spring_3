package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.dto.EmployeeDTO;
import org.utj.hrh.model.*;
import org.utj.hrh.repository.CountyRepository;
import org.utj.hrh.repository.EmployeeRepository;
import org.utj.hrh.security.MD5PasswordEncoder;
import org.utj.hrh.services.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final RoleService roleService;
    private final UserService userService;
    private final EmployeeRepository employeeRepository;
    private final CarderTypeService carderTypeService;
    private final EmployeeStatusService employeeStatusService;
    private final EmployeeService employeeService;
    private final EmployeePositionService employeePositionService;
    private final CountyService countyService;
    private final StandardCarderService standardCarderService;
    private final DesignationService designationService;
    @Autowired
    public EmployeeController(RoleService roleService, UserService userService, EmployeeRepository employeeRepository, CarderTypeService carderTypeService, EmployeeStatusService employeeStatusService, EmployeeService employeeService, EmployeePositionService employeePositionService, CountyService countyService, StandardCarderService standardCarderService, DesignationService designationService) {
        this.roleService = roleService;
        this.userService = userService;
        this.employeeRepository = employeeRepository;
        this.carderTypeService = carderTypeService;
        this.employeeStatusService = employeeStatusService;
        this.employeeService = employeeService;
        this.employeePositionService = employeePositionService;
        this.countyService = countyService;
        this.standardCarderService = standardCarderService;
        this.designationService = designationService;
    }

  public MD5PasswordEncoder md5PasswordEncoder;



    @GetMapping("/system/employee/all")
    public String getAllEmployees(Model model) {
        try {
            List<Employee> employeeList = employeeService.getAll();
            model.addAttribute("employees", employeeList);
            model.addAttribute("pageTitle", "STAFF");
            model.addAttribute("httpStatus", HttpStatus.OK);
            return "pages/admin/employee/employees";
        } catch (NullPointerException e) {
            // Log the error message and stack trace
            logger.error("An error occurred while fetching employees.", e);
            // Handle the exception, e.g., return an error page or message
            model.addAttribute("error", "An error occurred while fetching employees.");
            model.addAttribute("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR);
            return "/error/error-500"; // Create an error page view
        }
//        List<Employee> employeeList=employeeService.getAll();
////        System.out.println(employeeList.toString());
//        // Add the data to the model
//
//        model.addAttribute("employees", employeeList);
//        model.addAttribute("pageTitle", "STAFF");
//        model.addAttribute("httpStatus", HttpStatus.OK);
////        System.out.println(model);
//        return "pages/admin/employee/employees";
    }

    // Mapping for displaying the add new product form
    @GetMapping("system/employee/form")
    public String showAddNewForm(Model model) {
            // Create operation, set the create flag
            model.addAttribute("createMode", true);
            Employee employee = new Employee();
            model.addAttribute("employee",employee);
            List<EmployeeStatus> employeeStatuses=employeeStatusService.getAll();
            model.addAttribute("employeeStatuses",employeeStatuses);
            List<CarderType> types = carderTypeService.getAll();
            model.addAttribute("carder_types", types);
            List<StandardCarder> standardCarders = standardCarderService.getAll();
            model.addAttribute("standardCarders", standardCarders);
            List<County> counties = countyService.getActive();
            model.addAttribute("counties",counties);

        return "pages/admin/employee/add-employee"; // This corresponds to the Thymeleaf template
    }
    @GetMapping("system/employee/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        // Edit operation, load data and set an edit flag
        model.addAttribute("editMode", true);
        Employee employee_ = employeeRepository.getEmployeeByEmp_no(id);
        model.addAttribute("employee", employee_);
        List<EmployeeStatus> employeeStatuses=employeeStatusService.getAll();
        model.addAttribute("employeeStatuses",employeeStatuses);
        List<CarderType> types = carderTypeService.getAll();
        model.addAttribute("carder_types", types);
        List<County> counties = countyService.getActive();
        model.addAttribute("counties",counties);
        List<Designation> designations = designationService.getAll();
        model.addAttribute("designations",designations);
        List<StandardCarder> standardCarders = standardCarderService.getAll();
        model.addAttribute("standardCarders",standardCarders);
        // Load and pass the data to populate the form fields
        return "pages/admin/employee/edit-employee"; // This corresponds to the Thymeleaf template
    }
//    @GetMapping("/employees/{id}/enabled/{status}")
//    public String updateStaffEnabledStatus(@PathVariable("id") String id, @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) throws EmployeeNotFoundException {
//        employeeRepository.updateEnabledStatus(id, enabled);
//        String status = enabled ? "enabled" : "disabled";
//        String message = "The user ID " + id + " has been " + status;
//        redirectAttributes.addFlashAttribute("message", message);
//        return "redirect:/users";
//    }

    // Add other CRUD operations for employees
//    @GetMapping("admin/employee/active")
//    public List<Employee> getActiveEmployees() {
//        int isActive =1;
//        return employeeRepository.fi(1);
//    }

    // Add other CRUD operations for employees
    @GetMapping("employee/list-all")
    public List<Employee> listAllEmployees() {

        return employeeRepository.findAll();
    }

    @GetMapping("/count/started-between")
    public Long getCountOfEmployeesStartedBetween() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate startDate = currentMonth.atDay(1);
        LocalDate endDate = currentMonth.atEndOfMonth();
        return employeeRepository.countByDateStartedBetween(startDate, endDate);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") String id) {
        Optional<Employee> employee = employeeRepository.findEmployeeById(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }


    @GetMapping("system/employee/view/{emp_no}")
    public String viewEmployee(@PathVariable(name="emp_no") String emp_no, Model model) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployee(emp_no);
        Optional<Supervisor> activeSupervisor = employeeService.getActiveSupervisorForEmployee(emp_no);

        if (activeSupervisor.isPresent()) {
            Supervisor supervisor = activeSupervisor.get();
            // Do something with the supervisor
            String supervisorName = supervisor.getPerson().getName();
            model.addAttribute("supervisorName", supervisorName);
        } else {
            String supervisorName = "Not Assigned";
            model.addAttribute("supervisorName", supervisorName);
        }
//        System.out.println(activeSupervisor.toString());
        model.addAttribute("employee", employee);


//                System.out.println(employee);
        model.addAttribute("employee", employee);
//
        List<EmployeeStatus> employeeStatuses=employeeStatusService.getAll();
        model.addAttribute("employeeStatuses",employeeStatuses);
        List<CarderType> types = carderTypeService.getAll();
        model.addAttribute("carder_types", types);
        EmployeeEmergencyContact employeeEmergencyContact = new EmployeeEmergencyContact();
        model.addAttribute("employeeEmergencyContact",employeeEmergencyContact);
        List<County> counties = countyService.getActive();
        model.addAttribute("counties",counties);
        List<Designation> designations = designationService.getAll();
        model.addAttribute("designations",designations);
        List<StandardCarder> standardCarders = standardCarderService.getAll();
        model.addAttribute("standardCarders",standardCarders);
        model.addAttribute("pageTitle","View ::  Employee Profile");
        return "pages/admin/employee/view-profile";

    }

    @PostMapping("/system/employee/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) throws DesignationNotFoundException {
        // Save the employee data to the database or perform other necessary actions
        EmployeePosition employeePosition= employeePositionService.getDesignation(employee.getPosition().getId());
        employee.setPosition(employeePosition);
//        employee.setId(employee.getEmpNo());
        employeeService.save(employee);
        User user= new User();
        // Create a username based on the rules
        if (employee.isHasLoginAccess()) {
            String firstName = employee.getFirstName();
            String surname = employee.getSurname();
            // Check if first name and surname are not empty
            if (firstName != null && !firstName.isEmpty() && surname != null && !surname.isEmpty()) {
                // Create the username by taking the first letter of the first name and concatenating it with the surname
                String username = firstName.substring(0, 1).toLowerCase() + surname.toLowerCase();
                user.setUsername(username);
            }
//            user.setPerson(user.getPerson().setPersonNumber());
            // Ensure that md5PasswordEncoder is not null before using it
            if (md5PasswordEncoder != null) {
                String password = employee.getNationalId(); // Use empNo as the initial password
                String encryptedPassword = md5PasswordEncoder.encode(password); // Password hashing
                user.setPassword(encryptedPassword);
            } else {
                        logger.info("Error Encripting  form data: " + employee.getNationalId());
            }

            String role_name="Employee";
            Role role =roleService.getRoleByName(role_name);
            user.setRole(role);
            user.setStatus(true);
            userService.save(user);

        }

//        String empNumber = employee.getEmpNo();
//        System.out.println(employee.toString());
//        logger.info("Received form data: " + employee.toString());
        // Redirect to a success page or another appropriate page
//        return "redirect:/system/employee/view/" + empNumber;
        return null;
    }

}