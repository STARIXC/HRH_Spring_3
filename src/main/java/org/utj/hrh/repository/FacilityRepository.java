package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Facility;
import org.utj.hrh.model.SubCounty;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Integer> {
    @Query("SELECT f from Facility f ")
    List<Facility> findByActive(@Param("active") Integer active);

    @Query("SELECT fac FROM Facility fac WHERE fac.district_id = :districtID  order by fac.sub_partner_nom ASC ")
     List<Facility> fetchDataFromDataSource(@Param("districtID") Integer districtID);
    @Query("SELECT fd from Facility fd  where fd.centre_sante_id=:centreSanteID")
    String findByMFL(@Param("centreSanteID") Integer centreSanteID);

//    Long countCountyByCountyID(Integer id);
//List<Facility> findBySubCounty(SubCounty subCounty);

}
