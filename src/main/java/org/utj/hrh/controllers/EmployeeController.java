package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.model.Employee;
import org.utj.hrh.repository.EmployeeRepository;
import org.utj.hrh.services.EmployeeNotFoundException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/system/employee/all")
    public String getAllEmployees(Model model) {
        List<Employee> employeeList=employeeRepository.findAll();
        // Add the data to the model
        model.addAttribute("employees", employeeList);
        model.addAttribute("pageTitle", "STAFF");
        model.addAttribute("httpStatus", HttpStatus.OK);
//        System.out.println(model);
        return "pages/employees";
    }

    // Mapping for displaying the add new product form
    @GetMapping("system/employee/add-new")
    public String showAddNewForm() {
        // You can add any necessary data to the model here
        return "pages/add-employee"; // This corresponds to the Thymeleaf template
    }

    @GetMapping("/employees/{id}/enabled/{status}")
    public String updateStaffEnabledStatus(@PathVariable("id") String id, @PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) throws EmployeeNotFoundException {
        employeeRepository.updateEnabledStatus(id, enabled);
        String status = enabled ? "enabled" : "disabled";
        String message = "The user ID " + id + " has been " + status;
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/users";
    }

    // Add other CRUD operations for employees
    @GetMapping("admin/employee/active")
    public List<Employee> getActiveEmployees() {
        int isActive =1;
        return employeeRepository.findByStatus(1);
    }

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

}