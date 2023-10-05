package org.utj.hrh.helper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ADMIN")) {
                response.sendRedirect("/admin/"); // Redirect for users with ROLE_ADMIN
                return;
            } else if (authority.getAuthority().equals("EMPLOYEE")) {
                response.sendRedirect("/system/"); // Redirect for users with ROLE_USER
                return;
            }
        }
        response.sendRedirect("/home"); // Default redirect if no role-specific redirect is defined
    }
}