package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Role;
import org.utj.hrh.model.User;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query("SELECT r FROM Role r WHERE r.role_name = :role_name")
    Role findByRoleName(@Param("role_name") String role_name);

}
