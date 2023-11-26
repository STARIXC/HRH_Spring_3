package org.utj.hrh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utj.hrh.repository.EmployeeRepository;

import java.time.LocalDate;

@Controller
public class ApplicationController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("system/dashboard")
    public String dashboard(Model model) {
        LocalDate today = LocalDate.now();

Long newEmployees= employeeRepository.countByDateStartedBetween(today.withDayOfMonth(1),today.withDayOfMonth(today.lengthOfMonth()));
        Integer totalEmployees = Math.toIntExact(employeeRepository.count()); // Replace with actual data retrieval
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("newEmployees", newEmployees);
        return "pages/admin/landing-page";
    }
    @GetMapping("employee/dashboard")
    public String employeeDashboard(Model model) {

        return "pages/staff/landing-page";
    }
    @GetMapping("/error-403")
    public String goEmployeeHome() {
        return "error/accessDenied";
    }
//    @GetMapping("/supervisor/")
//    public String goSupervisorHome() {
//        return "pages/landing-page";
//    }
//    @GetMapping("/hrh/")
//    public String goHRHAdminHome() {
//        return "pages/landing-page";
//    }
//    @GetMapping("/to/")
//    public String goHome() {
//        return "pages/landing-page";
//    }
//
//    @RequestMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    @RequestMapping("/logout-success")
//    public String logoutPage() {
//        return "logout";
//    }

}
