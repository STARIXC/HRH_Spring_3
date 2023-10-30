package org.utj.hrh.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.RolePermission;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {

    // Define custom query methods for role permission-related operations

    // Example: Find role permissions by role ID
    List<RolePermission> findByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);

    // Add more custom query methods as needed
}