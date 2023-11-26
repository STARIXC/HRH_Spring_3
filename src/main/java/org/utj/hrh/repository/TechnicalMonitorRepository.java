package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Admin;
import org.utj.hrh.model.TechnicalMonitor;

import java.util.Optional;

@Repository
public interface TechnicalMonitorRepository extends JpaRepository<TechnicalMonitor, Long> {
//    TechnicalMonitor findByLoginId(String loginId); // Implement a method to fetch technical monitor data by login ID
Optional<TechnicalMonitor> findByPersonPersonNumber(String personNumber); // Add this method
}