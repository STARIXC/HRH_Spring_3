package org.utj.hrh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @GetMapping("/system")
    public String goAdminHome() {
        return "pages/landing-page";
    }

    @GetMapping("/employee/")
    public String goEmployeeHome() {
        return "pages/landing-page";
    }
    @GetMapping("/supervisor/")
    public String goSupervisorHome() {
        return "pages/landing-page";
    }
    @GetMapping("/hrh/")
    public String goHRHAdminHome() {
        return "pages/landing-page";
    }
    @GetMapping("/to/")
    public String goHome() {
        return "pages/landing-page";
    }
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
