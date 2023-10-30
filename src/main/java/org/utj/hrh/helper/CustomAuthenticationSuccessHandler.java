package org.utj.hrh.helper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.utj.hrh.services.UserService;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username= authentication.getName();
//        Fet the user's role from the database
        String userRole=userService.determineUserRole(username);


        if ("unknown".equals(userRole)) {
            // Handle unknown role (e.g., show an error page)
            response.sendRedirect(request.getContextPath() + "/unknown-role-page");
        } else {
            String userSpecificData = fetchUserSpecificData(userRole);
            storeUserSpecificDataInSession(request, userSpecificData);
            redirectToTargetPage(request, response, userRole);
        }

    }
    private String fetchUserSpecificData(String userRole) {
        // Replace this with your database queries to fetch user-specific data
        // For simplicity, we're using dummy data
        return "User-specific data for role: " + userRole;
    }

    private void storeUserSpecificDataInSession(HttpServletRequest request, String userSpecificData) {
        request.getSession().setAttribute("userSpecificData", userSpecificData);
    }

    private void redirectToTargetPage(HttpServletRequest request, HttpServletResponse response, String userRole) throws IOException {
        String targetUrl;
        if ("employee".equals(userRole)) {
            targetUrl = "/employee/dashboard";
        } else if ("supervisor".equals(userRole)) {
            targetUrl = "/supervisor/dashboard";
        } else if ("manager".equals(userRole)) {
            targetUrl = "/manager/dashboard";
        } else {
            targetUrl = "/unknown-role-page";
        }
        response.sendRedirect(request.getContextPath() + targetUrl);
    }
}
