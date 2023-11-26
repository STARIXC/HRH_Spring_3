package org.utj.hrh.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Menu;
import org.utj.hrh.model.MenuPermission;
import org.utj.hrh.repository.MenuPermissionRepository;
import org.utj.hrh.repository.MenuRepository;
import org.utj.hrh.repository.ModuleRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ModuleRepository moduleRepository;
@Autowired
    private MenuPermissionRepository menuPermissionRepository;




    public Map<String, Object> getAllMenu(int roleId) {
        List<Menu> allMenus = menuRepository.findByStatusAndMenuUrlIsNotNullOrderByModuleId(1);
        List<MenuPermission> permissions = menuPermissionRepository.findByRoleId(roleId);

        List<Menu> subMenu = new ArrayList<>();
        Map<String, Map<String, Menu>> arrayFormat = new HashMap<>();

        for (Menu menu : allMenus) {
            boolean hasPermission = permissions.stream()
                    .anyMatch(permission -> permission.getMenu().getId().equals(menu.getId()));
            // Use a local variable to store the hasPermission value
            String hasPermissionValue = hasPermission ? "yes" : "no";
//            menu.setHasPermission(hasPermission ? "yes" : "no");
            // Add this check to filter by hasPermission
            if (hasPermissionValue.equals("yes")) {
                if (menu.getAction() != null && !menu.getAction().isEmpty()) {
                    subMenu.add(menu);
                }

                if (menu.getAction() == null || menu.getAction().isEmpty()) {
                    String moduleName = menu.getModule().getName();
                    String menuName = menu.getName();

                    arrayFormat.computeIfAbsent(moduleName, k -> new HashMap<>())
                            .put(menuName, menu);
                }
            }
//            if (menu.getAction() != null && !menu.getAction().isEmpty()) {
//                subMenu.add(menu);
//            }
//
//            if (menu.getAction() == null || menu.getAction().isEmpty()) {
//                String moduleName = menu.getModule().getName();
//                String menuName = menu.getName();
//
//                arrayFormat.computeIfAbsent(moduleName, k -> new HashMap<>())
//                        .put(menuName, menu);
//            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("arrayFormat", arrayFormat);
        result.put("subMenu", subMenu);

        return result;
    }
}
