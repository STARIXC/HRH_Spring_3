package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.License;

@Repository
public interface LicenseRepository extends JpaRepository<License,Long> {
    License findByName(String name);

    Long countById(Long id);
}
