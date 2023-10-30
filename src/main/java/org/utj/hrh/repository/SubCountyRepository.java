package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.County;
import org.utj.hrh.model.StandardCarder;
import org.utj.hrh.model.SubCounty;

import java.util.List;

@Repository
public interface SubCountyRepository extends JpaRepository<SubCounty,Integer> {
    @Query("SELECT s from SubCounty s where s.active =:active")
    List<SubCounty> findByActive(@Param("active") Integer active);

    @Query("SELECT sc FROM SubCounty sc WHERE sc.county_id = :countyId and sc.active=:active order by sc.district_nom ASC ")
     List<SubCounty> fetchDataFromDataSource(@Param("countyId") Integer countyId,@Param("active") Integer active);
//
////    Long countCountyByCountyID(Integer id);
//List<SubCounty> findByCountyAndActive(County county,Integer active);

}
