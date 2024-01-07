package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.PositionChangeHistory;

@Repository
public interface PositionChangeRepository extends JpaRepository<PositionChangeHistory,Long> {

}
