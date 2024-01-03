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
//    @Query("SELECT sc.districtId,sc.districtName,sc.county FROM SubCounty sc WHERE sc.county.countyId = :countyId and sc.active=1 order by sc.districtName  ASC ")
//    List<SubCounty> fetchDataFromDataSource(@Param("countyId") Integer countyId);
    @Query("SELECT NEW org.utj.hrh.dto.SubCountyDTO(sc.districtId, sc.districtName, sc.county) FROM SubCounty sc WHERE sc.county.countyId = :countyId and sc.active = 1 ORDER BY sc.districtName ASC")
    List<SubCountyDTO> fetchDataFromDataSource(@Param("countyId") Integer countyId);


}
