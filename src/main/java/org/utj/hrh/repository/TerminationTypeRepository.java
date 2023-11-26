package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.TerminationType;

@Repository
public interface TerminationTypeRepository extends JpaRepository<TerminationType,Integer> {
    long countById(Integer id);
}
