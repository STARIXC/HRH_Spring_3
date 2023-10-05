package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.utj.hrh.helper.JSONConverter;
import org.utj.hrh.model.Menu;
import org.utj.hrh.model.MenuModule;
import org.utj.hrh.repository.MenuRepository;
import org.utj.hrh.repository.ModuleRepository;

import java.util.*;

@Controller
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ModuleRepository moduleRepository;


    @GetMapping("/menu")
    public String getMenuByRole(Model model) {
        try {
            String id="1";
            System.out.println("Menu ID:" + id);
            long roleId = Long.parseLong(id);
            System.out.println("Converted to ID:" + roleId);

            // Use repository methods to fetch data
            List<MenuModule> modules = moduleRepository.findAll();
            List<Menu> all_menu_by_id = menuRepository.findByRoleIdAndStatusAndActionIsNullOrderByidAsc(roleId);

            if (all_menu_by_id != null) {
                String result = processMenus(all_menu_by_id, modules);
                model.addAttribute("menus", result);
                return "menus";
            } else {
                return "Error: Modules or menu data is null.";
            }
        } catch (NumberFormatException e) {
            return "Error: Invalid menu ID format.";
        }
    }

    private String processMenus(List<Menu> menus, List<MenuModule> modules) {
        Map<Integer, Map<String, Object>> sideMenu = new HashMap<>();

        if (menus != null && modules != null) {
            for (Menu menu : menus) {
                int moduleId = -1;

                for (int i = 0; i < modules.size(); i++) {
                    if (modules.get(i).getId() == menu.getModule_id()) {
                        moduleId = i;
                        break;
                    }
                }

                if (moduleId != -1) {
                    int module_id = menu.getModule_id();

                    if (!sideMenu.containsKey(module_id)) {
                        sideMenu.put(module_id, new HashMap<String, Object>());
                        Map<String, Object> moduleMap = sideMenu.get(module_id);

                        moduleMap.put("id", modules.get(moduleId).getId());
                        moduleMap.put("name", modules.get(moduleId).getName());

                        // Use the "icon_class" from the associated MenuModule
                        moduleMap.put("icon_class", modules.get(moduleId).getIconClass());

                        moduleMap.put("menu_url", "#");
                        moduleMap.put("parent_id", "");
                        moduleMap.put("module_id", modules.get(moduleId).getId());

                        // Use a list for sub_menu instead of a map
                        moduleMap.put("sub_menu", new ArrayList<Map<String, Object>>());
                    }

                    if (menu.getParent_id() == 0) {
                        List<Map<String, Object>> subMenu = (List<Map<String, Object>>) sideMenu.get(module_id).get("sub_menu");

                        // Create a map representation of the Menu object and add it to subMenu
                        Map<String, Object> menuMap = createMenuMap(menu);
                        subMenu.add(menuMap);
                    } else {
                        int parentId = menu.getParent_id();
                        List<Map<String, Object>> subMenu = (List<Map<String, Object>>) sideMenu.get(module_id).get("sub_menu");

                        for (Map<String, Object> menuItem : subMenu) {
                            if (menuItem.get("id").equals(parentId)) {
                                List<Map<String, Object>> subSubMenu = (List<Map<String, Object>>) menuItem.get("sub_menu");

                                // Create a map representation of the Menu object and add it to subSubMenu
                                Map<String, Object> menuMap = createMenuMap(menu);
                                subSubMenu.add(menuMap);
                                break;
                            }
                        }
                    }
                }
            }
        }

        // Convert sideMenu to JSON using Gson
        String sideMenuJson = JSONConverter.convert(sideMenu);

        return sideMenuJson;
    }

    private Map<String, Object> createMenuMap(Menu menu) {
        Map<String, Object> menuMap = new HashMap<>();
        menuMap.put("id", menu.getId());
        menuMap.put("name", menu.getName());

        // Include other properties of the Menu object as needed
        menuMap.put("menu_url", menu.getMenu_url());
        menuMap.put("parent_id", menu.getParent_id());
        menuMap.put("module_id", menu.getModule_id());
        menuMap.put("sub_menu", new ArrayList<Map<String, Object>>());
        return menuMap;
    }

}
