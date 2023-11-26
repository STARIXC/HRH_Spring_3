package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.model.Role;
import org.utj.hrh.repository.RoleRepository;

import java.util.List;

@Controller
@RequestMapping("/system/roles")
public class RoleController {
@Autowired
    private RoleRepository roleRepository;


@GetMapping("/list")
    public String viewRoles(@RequestParam(name = "id", required = false) Integer roleId, Model model){
    // Check if roleId is provided; if not, it's a new role creation
    if (roleId == null) {
        // Create a new Role object for new role creation
        model.addAttribute("role", new Role());
    } else {
        // Retrieve the role from the database for editing
        Role role = roleRepository.getReferenceById(roleId);
        if (role == null) {
            // Handle the case where the role with the specified ID doesn't exist
            // You can redirect or display an error message here
            Role role_ = new Role();
            model.addAttribute("role", role_);
        } else {
            model.addAttribute("role", role);
        }
    }

    List<Role> roles =roleRepository.findAll();
    model.addAttribute("roles",roles);
    model.addAttribute("pageTitle", "Roles");
    return "pages/admin/Administration/roles";
}
    @PostMapping("/save")
    public String saveRole(@ModelAttribute("role") Role role) {
        if (role.getId() == null) {
            // It's a new role, so save it as a new record
            roleRepository.save(role);
        } else {
            // It's an edit, so update the existing role
            roleRepository.save(role);
        }
        return "redirect:/system/roles/list"; // Redirect back to the same page
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Integer id) {
        // Delete the role with the specified ID
        roleRepository.deleteById(id);
        return "redirect:/system/roles/list"; // Redirect back to the same page
    }

}
