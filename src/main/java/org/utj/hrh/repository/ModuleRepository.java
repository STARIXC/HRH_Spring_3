package org.utj.hrh.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.MenuItemModule;

@Repository
public interface ModuleRepository extends JpaRepository<MenuItemModule, Long> {
}