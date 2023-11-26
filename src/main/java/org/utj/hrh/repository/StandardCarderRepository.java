package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.StandardCarder;

import java.util.List;

@Repository
public interface StandardCarderRepository extends JpaRepository<StandardCarder,Integer> {
    @Query("SELECT s from StandardCarder s where s.standardized_cadre_name =:standardized_cadre_name ")
    StandardCarder getStandardCarderByStandardized_cadre_name(@Param("standardized_cadre_name") String standardized_cadre_name);

    long countById(Integer id);

    @Query("SELECT s FROM StandardCarder s WHERE s.carderCat.id = :carder_category_id")
    List<StandardCarder> findStandardCardersByCarderCategoryId(@Param("carder_category_id") Integer carder_category_id);

    @Query("SELECT s FROM StandardCarder s WHERE s.carderType.id = :carder_type_id")
    List<StandardCarder> findStandardCardersByCarderType(@Param("carder_type_id") Integer carder_type_id);
    List<StandardCarder> findByCarderType_Id(Integer carderTypeId);

}
