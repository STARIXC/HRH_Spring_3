package org.utj.hrh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @GetMapping("system/dashboard")
    public String dashboard() {
        return "pages/landing-page";
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
