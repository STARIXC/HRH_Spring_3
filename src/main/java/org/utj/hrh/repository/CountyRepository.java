package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Activity;
import org.utj.hrh.model.County;
import org.utj.hrh.model.Employee;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CountyRepository extends JpaRepository<County,Integer> {
    @Query("SELECT c from County c where c.isActive =:active")
    List<County> findByActive(@Param("active") String active);
//    Long countCountyByCountyID(Integer id);
}
