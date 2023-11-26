package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Role;
import org.utj.hrh.model.User;
import org.utj.hrh.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String determineUserRole(String username) {
        User user = getUserByUsername(username);

        if (user != null) {
            Role role = user.getRole();
            if (role != null) {
                return role.getRole_name(); // Assuming a "roleName" attribute in the Role entity
            }
        }

        return "unknown";
    }

    public void save(User user){
        userRepository.save(user);

    }

    public Integer getRoleID(String username) {
        User user = getUserByUsername(username);

        if (user != null) {
            Role role = user.getRole();
            if (role != null) {
                return role.getId(); // Assuming a "roleName" attribute in the Role entity
            }
        }
        return null;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean isUsernameTaken(String username) {
        // Check if a user with the given username already exists
        return userRepository.existsByUsername(username);
    }
}
