package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.MenuPermission;

import java.util.List;

@Repository
public interface MenuPermissionRepository extends JpaRepository<MenuPermission, Long> {
    List<MenuPermission> findByRoleId(int roleId);
}