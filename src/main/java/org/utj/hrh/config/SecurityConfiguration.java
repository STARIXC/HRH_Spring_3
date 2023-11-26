package org.utj.hrh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.utj.hrh.helper.CustomAuthenticationSuccessHandler;
import org.utj.hrh.repository.UserRepository;
import org.utj.hrh.security.MD5PasswordEncoder;
import org.utj.hrh.services.CustomUserDetailsService;
import org.utj.hrh.services.UserService;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfiguration {

    public static final String[] ENDPOINTS_WHITELIST = {
            "/assets/**",
            "/login",
            "/menus/**"
    };
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String DEFAULT_SUCCESS_URL = "/system/dashboard";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public SecurityConfiguration(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, UserDetailsService userDetailsService, UserService userService, UserRepository userRepository) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
//                        .requestMatchers("/system/**").access(new WebExpressionAuthorizationManager("hasRole('Admin')"))
                                .requestMatchers("/system/**").hasRole("Admin")
                                .requestMatchers("/employee/**").hasRole("Employee")
                                .requestMatchers("/hrh/**").hasRole("HR_Admin")
                                .requestMatchers("/payroll/**").hasRole("Payroll")
                                .requestMatchers("/supervisor/**").hasRole("Facility_Supervisor")
                                .requestMatchers("/technical_monitor/**").hasRole("TECHNICAL_MONITOR")
                                .anyRequest().authenticated()
                )
                .formLogin((formLogin) -> formLogin
                                .loginPage(LOGIN_URL)
                                .loginProcessingUrl(LOGIN_URL)
                                .failureUrl(LOGIN_FAIL_URL)
                                .usernameParameter(USERNAME)
                                .passwordParameter(PASSWORD)
                                .successHandler(customAuthenticationSuccessHandler)
//                        .defaultSuccessUrl(String.valueOf(customAuthenticationSuccessHandler))
                )
                .logout((logout) -> logout
                        .logoutUrl(LOGOUT_URL)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl(LOGIN_URL + "?logout")
                )
                .exceptionHandling((exception) -> exception.accessDeniedPage("/error-403"));

        return http.build();
    }

    //    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
////        return configuration.getAuthenticationManager();
//        return configuration.getAuthenticationManager();
//
//    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService securityUserDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(securityUserDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authProvider;
    }
}

