package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.utj.hrh.model.Employee;
import org.utj.hrh.repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("system/employee/all")
    public String getAllEmployees(Model model) {
        List<Employee> employeeList=employeeRepository.findAll();
        // Add the data to the model
        model.addAttribute("employees", employeeList);
        System.out.println(model);
        return "pages/employees";
    }

    // Mapping for displaying the add new product form
    @GetMapping("admin/employee/add-new")
    public String showAddNewForm() {
        // You can add any necessary data to the model here
        return "pages/add-employee"; // This corresponds to the Thymeleaf template
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


}