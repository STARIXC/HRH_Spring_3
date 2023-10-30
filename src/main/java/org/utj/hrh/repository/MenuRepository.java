package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.utj.hrh.model.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> getMenuDataById(int menu_id);

    @Query("SELECT m FROM Menu m " +
            "INNER JOIN MenuPermission mp ON m.id = mp.menu.id " +
            "WHERE mp.role_id = :roleId AND m.status = 1 AND m.action IS NULL " +
            "ORDER BY m.id ASC")
    List<Menu> findByRoleIdAndStatusAndActionIsNullOrderByidAsc(Long roleId);

    // Custom query method to retrieve menu permissions by role id
    @Query("SELECT m FROM Menu m JOIN MenuPermission mp ON m.id = mp.menu.id WHERE mp.role_id = ?1")
    List<Menu> findMenuPermissionsByRoleId(Long roleId);

    // Custom query method to retrieve all menus
    @Query("SELECT m FROM Menu m WHERE m.status = 1 AND m.menu_url IS NOT NULL ORDER BY m.module_id")
    List<Menu> findAllMenus();

    @Query("SELECT m FROM Menu m WHERE m.status = 1")
    List<Menu> findAllActiveMenus();
}
