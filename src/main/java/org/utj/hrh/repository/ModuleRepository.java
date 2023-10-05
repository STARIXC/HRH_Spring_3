package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utj.hrh.model.MenuModule;

public interface ModuleRepository extends JpaRepository<MenuModule, Long> {
}
