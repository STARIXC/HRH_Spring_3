package org.utj.hrh.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Menu;
import org.utj.hrh.model.Role;
import org.utj.hrh.model.RolePermission;
import org.utj.hrh.repository.MenuRepository;
import org.utj.hrh.repository.RolePermissionRepository;

import java.util.*;

@Service
public class RolePermissionService {
    private final RolePermissionRepository rolePermissionRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public RolePermissionService(RolePermissionRepository rolePermissionRepository, MenuRepository menuRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public Map<String, Object> getAllMenuForRole(Integer roleId) {
        List<Menu> allMenus = menuRepository.findAllActiveMenus(); // Implement a method in MenuRepository to fetch active menus
        List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleId(roleId);

        Map<String, Object> result = new HashMap<>();// Initialize a map to store the result

        // Loop through all menus and check for role permissions
        for (Menu menu : allMenus) {
            Map<String, Object> menuData = new HashMap<>();
            menuData.put("id", menu.getId());
            menuData.put("name", menu.getName());
            menuData.put("menu_url", menu.getMenu_url());
            menuData.put("parent_id", menu.getParent_id());
            menuData.put("module_id", menu.getModule().getId());

            RolePermission rolePermission = findRolePermissionForMenu(rolePermissions, menu.getId());
            menuData.put("hasPermission", rolePermission != null ? "yes" : "no");

            // Add the menuData to the result map based on module and name
            String moduleName = menu.getModule().getName();
            if (!result.containsKey(moduleName)) {
                result.put(moduleName, new LinkedHashMap<>());
            }
            ((Map<String, Object>) result.get(moduleName)).put(menu.getName(), menuData);
        }

        return result;
    }

    private RolePermission findRolePermissionForMenu(List<RolePermission> rolePermissions, Long menuId) {
        for (RolePermission rolePermission : rolePermissions) {
            if (rolePermission.getMenu().getId().equals(menuId)) {
                return rolePermission;
            }
        }
        return null;
    }

    @Transactional
    public void updateRolePermissions(Integer roleId, List<Long> menuIds) {
        rolePermissionRepository.deleteByRoleId(roleId);
        if (menuIds != null && !menuIds.isEmpty()) {
            List<RolePermission> rolePermissions = new ArrayList<>();
            for (Long menuId : menuIds) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRole(new Role( roleId));
                rolePermission.setMenu(new Menu(menuId));
                rolePermissions.add(rolePermission);
            }
            rolePermissionRepository.saveAll(rolePermissions);
        }
    }
}
