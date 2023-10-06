package org.utj.hrh.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.utj.hrh.helper.CustomAuthenticationSuccessHandler;
import org.utj.hrh.model.User;
import org.utj.hrh.repository.UserRepository;
import org.utj.hrh.security.MD5PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfiguration  {
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


    private UserDetailsService userDetailsService;
    public static final String[] ENDPOINTS_WHITELIST = {
            "/assets/**",
            "/login"
    };

    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String DEFAULT_SUCCESS_URL = "/system/dashboard";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Autowired
    private UserRepository  userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MD5PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
//                        .requestMatchers("/system/**").hasRole("Super_Admin")
//                        .requestMatchers("/hrh/**").hasRole("HR_Admin")
//                        .requestMatchers("/payroll/**").hasRole("Payroll")
//                        .requestMatchers("/supervisor/**").hasRole("Facility_Supervisor")
//                        .requestMatchers("/technical_monitor/**").hasRole("TECHNICAL_MONITOR")
                        .anyRequest().authenticated()
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage(LOGIN_URL)
                        .loginProcessingUrl(LOGIN_URL)
                        .failureUrl(LOGIN_FAIL_URL)
                        .usernameParameter(USERNAME)
                        .passwordParameter(PASSWORD)
                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL)
                )
                .logout((logout) -> logout
                        .logoutUrl(LOGOUT_URL)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl(LOGIN_URL + "?logout")
                )
        .exceptionHandling((exception)-> exception.accessDeniedPage("/error-403"));

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

