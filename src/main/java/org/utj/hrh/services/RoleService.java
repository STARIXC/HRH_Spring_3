package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Role;
import org.utj.hrh.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role getRoleByName(String role_name){
        return roleRepository.findByRoleName(role_name);
    }
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}
