package org.utj.hrh.helper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.utj.hrh.model.*;
import org.utj.hrh.repository.*;
import org.utj.hrh.services.MenuService;
import org.utj.hrh.services.UserService;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private TechnicalMonitorRepository technicalMonitorRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PersonRepository personRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        String username = authentication.getName();
        // Get the user's role from the database
        String userRole = userService.determineUserRole(username);

        User authUser=retrieveUserId(authentication);
        if ("unknown".equals(userRole)) {

            // Handle unknown role (e.g., show an error page)
            response.sendRedirect(request.getContextPath() + "/unknown-role-page");
        } else {
            Integer user_role_id= userService.getRoleID(username);
// String userSpecificData = fetchUserSpecificData(userRole,login_id);
            System.out.println("Login_ID:"+authUser);
            String login_id=authUser.getPerson().getPersonNumber();
            Person userSpecificData = personRepository.findPersonByPersonNumber(login_id);
            String personName=userSpecificData.getName();
            System.out.println("Logged in Full Name"+personName);
 // Store the user's role as a session attribute
            request.getSession().setAttribute("userRole", userRole);
            request.getSession().setAttribute("userDetails", personName);

            // Redirect to the target page based on the user's role
            redirectToTargetPage(request, response, userRole);
        }




    }

    // Method to retrieve the user's ID from the Authentication object
    private User retrieveUserId(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;

            // Retrieve the user ID from the UserDetails
            User authenticatedUser = userService.getUserByUsername(userDetails.getUsername()); // Or any method you use to get the user ID

            return authenticatedUser;
        } else if (principal instanceof String) {
            // If the principal is a String, it might be the username or ID
            return (User) principal;
        } else {
            // Handle other cases as needed
            return null;
        }

    }

    private String fetchUserSpecificData(String userRole, String authenticatedUserId) {

//        switch (userRole) {
//            case "admin":
//                Optional<Admin> admin = adminRepository.findByPersonPersonNumber(authenticatedUserId);
//                return "Admin-specific data for user: " + admin;
//
//            case "supervisor":
//                Optional<Supervisor> supervisor = supervisorRepository.findByPersonPersonNumber(authenticatedUserId);
//                return "Supervisor-specific data for user: " + supervisor;
//
//            case "technical_monitor":
//                Optional<TechnicalMonitor>  technicalMonitor = technicalMonitorRepository.findByPersonPersonNumber(authenticatedUserId);
//                return "Technical Monitor-specific data for user: " + technicalMonitor;
//
//            default:
//                return "User-specific data not found for role: " + userRole;
//        }
        return null;
    }


//    private void storeUserSpecificDataInSession(HttpServletRequest request, Optional<Person> userSpecificData) {
//        request.getSession().setAttribute("userSpecificData", userSpecificData);
//    }
//    private void storeMenuDataInSession(HttpServletRequest request, Map<String, Object> menuData) {
//        // Store menu data in the session
//        request.getSession().setAttribute("menuData", menuData);
//    }

    private void redirectToTargetPage(HttpServletRequest request, HttpServletResponse response, String userRole) throws IOException {
        System.out.println("User Role: "+userRole);
        String targetUrl;
        if ("Admin".equals(userRole)) {
            targetUrl = "/system/dashboard";
        } else if ("Employee".equals(userRole)) {
            targetUrl = "/employee/dashboard";
        }  else if ("Facility_Supervisor".equals(userRole)) {
            targetUrl = "/supervisor/dashboard";
        } else if ("Technical_Monitor".equals(userRole)) {
            targetUrl = "/technical_monitor/dashboard";
        } else {
            targetUrl = "/unknown-role-page";
        }
        response.sendRedirect(request.getContextPath() + targetUrl);
    }
}
