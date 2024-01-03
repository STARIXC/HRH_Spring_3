package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.EducationLevel;
import org.utj.hrh.model.Role;

@Repository
public interface EducationRepository extends JpaRepository<EducationLevel,Long> {
    EducationLevel findByName(String name);

    Long countById(Long id);
}
