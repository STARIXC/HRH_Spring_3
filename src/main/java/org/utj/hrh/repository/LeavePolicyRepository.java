package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.FinancialYear;
import org.utj.hrh.model.LeavePolicy;

import java.util.List;

@Repository
public interface LeavePolicyRepository extends JpaRepository<LeavePolicy,Long> {

    Long countById(Long id);
    List<LeavePolicy> findLeavePoliciesByFinancialYear(FinancialYear financialYear);
}
