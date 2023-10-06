package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {


}
