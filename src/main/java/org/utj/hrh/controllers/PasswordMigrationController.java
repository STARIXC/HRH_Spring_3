package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.utj.hrh.model.User;
import org.utj.hrh.repository.UserRepository;

import java.util.List;

@Controller
public class PasswordMigrationController {

    @Autowired
    private UserRepository userRepository; // Adjust this based on your data store

    @GetMapping("/migration")
    public String showMigrationPage(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "migration";
    }

    @PostMapping("/migratePasswords")
    public String migratePasswords(Model model) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            String password = user.getPassword();

            if (isMD5(password)) {
                // Only migrate if the password is MD5 encrypted
                String bcryptPassword = bcryptPasswordEncoder.encode(password);
                user.setPassword(bcryptPassword);
                userRepository.save(user);
            }
        }

        model.addAttribute("migrationSuccess", true);
        return "migration";
    }

    private boolean isMD5(String password) {
        // Implement your logic to check if the password is MD5 encrypted
        // For simplicity, we'll assume here that MD5 hashed passwords are 32 characters long
        return password.length() == 32;
    }
}
