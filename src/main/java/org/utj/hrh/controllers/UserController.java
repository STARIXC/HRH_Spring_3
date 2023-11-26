package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utj.hrh.model.Person;
import org.utj.hrh.model.Role;
import org.utj.hrh.model.User;
import org.utj.hrh.repository.PersonRepository;
import org.utj.hrh.services.RoleService;
import org.utj.hrh.services.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/system/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final PersonRepository personRepository;
    private final RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PersonRepository personRepository, RoleService roleService) {
        this.userService = userService;
        this.personRepository = personRepository;
        this.roleService = roleService;

    }


    @GetMapping("/viewSystemUsers")
    public String viewSystemUsers(Model model) {
        try {
            List<User> users = userService.getAll();
            model.addAttribute("users", users);
            model.addAttribute("recordCount", users.size());
            model.addAttribute("pageTitle", "View :: Users");
            model.addAttribute("httpStatus", HttpStatus.OK);
            return "/pages/admin/user/user/system-user";

        } catch (NullPointerException e) {
            // Log the error message and stack trace
            logger.error("An error occurred while fetching users.", e);
            // Handle the exception, e.g., return an error page or message
            model.addAttribute("error", "An error occurred while fetching employees.");
            model.addAttribute("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR);
            return "/error/error-500"; // Create an error page view
        }

    }

    @GetMapping("/add-new")
    public String showAddNewForm(Model model) {
        // Create operation, set the create flag
        model.addAttribute("createMode", true);
        User user = new User();
        model.addAttribute("user", user);
        List<Person> people = personRepository.findAll();
        model.addAttribute("people", people);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "pages/admin/user/user/add-system-user"; // This corresponds to the Thymeleaf template
    }

    @PostMapping("/migratePasswords")
    public String migratePasswords(Model model) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        List<User> users = userService.getAll();

        for (User user : users) {
            String password = user.getPassword();

            if (isMD5(password)) {
                // Only migrate if the password is MD5 encrypted
                String bcryptPassword = bcryptPasswordEncoder.encode(password);
                user.setPassword(bcryptPassword);
                userService.save(user);
            }
        }

        model.addAttribute("migrationSuccess", true);
        return "/pages/admin/user/user/system-user";
    }

    public boolean isMD5(String password) {
        return password.matches("^[A-Fa-f0-9]{32}$");
    }


    @PostMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        // Check if the username is already taken
        if (userService.isUsernameTaken(user.getUsername())) {
            model.addAttribute("registrationError", true);
            // You might want to add other attributes or logic based on the error
            return "pages/admin/user/user/add-system-user"; // Redirect back to the registration form with an error message
        }

        // Set timestamps and defaults if not provided
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (user.getCreated_at() == null) {
            user.setCreated_at(currentDateTime);
        }
        user.setUpdated_at(currentDateTime);
        user.setStatus(user.isStatus());
        user.setDeleted(false);

        // Bcrypt encode the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Set the role if available in the form
        if (user.getRole() != null && user.getRole().getId() != null) {
            // Fetch the role from the database based on the selected role id
            Role role = roleService.findRoleById(user.getRole().getId());
            user.setRole(role);
        }

        // Set the person if available in the form
        if (user.getPerson() != null && user.getPerson().getPersonNumber() != null) {
            // Fetch the person from the database based on the selected person number
            Person person = personRepository.findByPersonNumber(user.getPerson().getPersonNumber());
            user.setPerson(person);
        }
        System.out.println(user);
        // Save the user using your user service
        userService.save(user);

        // Redirect to a success page or login page
//        return "redirect:/system/user/viewSystemUsers";
        return null;
    }


}
