package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.DocumentType;
import org.utj.hrh.model.Termination;

@Repository
public interface TerminationsRepository extends JpaRepository<Termination,Integer> {

    long countByTerminationId(Integer id);
}
