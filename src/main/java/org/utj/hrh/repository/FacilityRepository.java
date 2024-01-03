package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.dto.FacilityDTO;
import org.utj.hrh.dto.SubCountyDTO;
import org.utj.hrh.model.Facility;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.model.Supervisor;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Integer> {
    @Query("SELECT f from Facility f ")
    List<Facility> findByActive(@Param("active") Integer active);

//    @Query("SELECT fac FROM Facility fac WHERE fac.county.countyId = :districtID and fac.active=1 order by fac.districtName  ASC ")
//     List<Facility> fetchDataFromDataSource(@Param("districtID") Integer districtID);
@Query("SELECT NEW org.utj.hrh.dto.FacilityDTO(fac.subPartnerId,fac.subPartnerName,fac.subCounty,fac.centreSanteId) FROM Facility fac WHERE fac.subCounty.districtId = :districtID and fac.active=1 order by fac.subPartnerName ASC")
List<FacilityDTO> fetchDataFromDataSource(@Param("districtID") Integer districtID);
    @Query("SELECT fd from Facility fd  where fd.centreSanteId=:centreSanteID")
    String findByMFL(@Param("centreSanteID") Integer centreSanteID);
    @Query("SELECT s FROM Supervisor s WHERE s.facilitySupervisors.centreSanteId = :facility AND s.status = 1")
    Supervisor getActiveSupervisor(@Param("facility") Facility facility);

//    Long countCountyByCountyID(Integer id);
//List<Facility> findBySubCounty(SubCounty subCounty);

}
