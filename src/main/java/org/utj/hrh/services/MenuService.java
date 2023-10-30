package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Menu;
import org.utj.hrh.repository.MenuRepository;

import java.util.*;

@Service
public class MenuService {


    @Autowired
    private MenuRepository menuRepository; // You will need to create a MenuRepository interface

    public Map<String, Object> getAllMenu(Long roleId) {
        List<Menu> permissions = menuRepository.findMenuPermissionsByRoleId(roleId);
        List<Menu> allMenus = menuRepository.findAllMenus();

        Map<Long, List<Menu>> subMenuMap = new HashMap<>();
        Map<String, Map<String, Menu>> arrayFormat = new HashMap<>();

        for (Menu allMenu : allMenus) {
            boolean hasPermission = permissions.stream()
                    .anyMatch(permission -> permission.getId().equals(allMenu.getId()));

//            allMenu.setHasPermission(hasPermission ? "yes" : "no");
//
//            if (allMenu.getAction() != null && !allMenu.getAction().isEmpty()) {
//                subMenuMap.computeIfAbsent(allMenu.getParentId(), k -> new ArrayList<>()).add(allMenu);
//            }
//
//            if (allMenu.getAction() == null || allMenu.getAction().isEmpty()) {
//                arrayFormat
//                        .computeIfAbsent(allMenu.getModuleName(), k -> new HashMap<>())
//                        .put(allMenu.getName(), allMenu);
//            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("arrayFormat", arrayFormat);
        result.put("subMenu", subMenuMap);
        return result;
    }
}
