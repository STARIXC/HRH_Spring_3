package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.utj.hrh.model.LeaveEntitlement;

import java.util.List;

@Repository
public interface LeaveEntitlementRepository extends JpaRepository<LeaveEntitlement,Integer> {

    Long countById(Integer id);
    List<LeaveEntitlement> findByEmployeeLeaveEntitlementId(Long employeeId);
    
}
