package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.User;
import org.utj.hrh.repository.UserRepository;

import java.util.List;

@Service
public class PasswordMigrationService {

    private final UserRepository userRepository; // Adjust this based on your data store

    private  final PasswordEncoder passwordEncoder;
@Autowired
    public PasswordMigrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void migratePasswords() {
        List<User> users = userRepository.findAll();

        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

        for (User user : users) {
            String md5Password = user.getPassword(); // Replace with the actual method to get MD5 hashed password
            String bcryptPassword = bcryptPasswordEncoder.encode(md5Password);
            user.setPassword(bcryptPassword);
            userRepository.save(user);
        }
    }
}
