package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.LeavePolicy;

@Repository
public interface LeavePolicyRepository extends JpaRepository<LeavePolicy,Integer> {

    Long countById(Integer id);
}
