package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.dto.SubCountyDTO;
import org.utj.hrh.model.Facility;
import org.utj.hrh.model.SubCounty;

import java.util.List;

@Repository
public interface SubCountyRepository extends JpaRepository<SubCounty,Integer> {
    @Query("SELECT s from SubCounty s where s.active =:active")
    List<SubCounty> findByActive(@Param("active") Integer active);

    List<SubCounty> findByCountyCountyIdAndActive(Integer countyId, int i);
}
