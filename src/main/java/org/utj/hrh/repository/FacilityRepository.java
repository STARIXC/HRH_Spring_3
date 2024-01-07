package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.dto.FacilityDTO;
import org.utj.hrh.model.Facility;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.model.Supervisor;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Integer> {
    @Query("SELECT f from Facility f ")
    List<Facility> findByActive(@Param("active") Integer active);

    @Query("SELECT fd from Facility fd  where fd.centreSanteId=:centreSanteID")
    String findByMFL(@Param("centreSanteID") Integer centreSanteID);
    @Query("SELECT s FROM Supervisor s WHERE s.facilitySupervisors.centreSanteId = :facility AND s.status = 1")
    Supervisor getActiveSupervisor(@Param("facility") Facility facility);
    
    List<Facility> findBySubCountyAndActive(SubCounty subcounty, int i);
}
