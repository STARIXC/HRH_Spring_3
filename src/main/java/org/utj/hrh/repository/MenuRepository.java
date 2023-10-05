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


}
