package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.utj.hrh.model.FinancialYear;

public interface FyRepository extends JpaRepository<FinancialYear,Long> {

}
