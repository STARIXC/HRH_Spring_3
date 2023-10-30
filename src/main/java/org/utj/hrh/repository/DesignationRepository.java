package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Designation;
import org.utj.hrh.model.StandardCarder;

import java.util.List;

@Repository
public interface DesignationRepository  extends JpaRepository<Designation, Integer> {
    @Query("SELECT d from Designation d where d.position_title =:position_title ")
    public Designation getDesignationByPosition_title(@Param("position_title") String position_title);

    public long countById(Integer id);

    @Query("SELECT p FROM Designation p WHERE p.standard_carder.id= :standardized_cadre_id")
    List<Designation> findPositionsByStandardized_cadre_id(@Param("standardized_cadre_id") Integer standardized_cadre_id);

}
