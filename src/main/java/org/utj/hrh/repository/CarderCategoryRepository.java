package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.CarderCategory;

@Repository
public interface CarderCategoryRepository extends JpaRepository<CarderCategory,Integer> {
    @Query("SELECT c from CarderCategory c where c.carderCategoryName =:cadre_category_name ")
    CarderCategory getCarderTypeByCadre_category_name(@Param("cadre_category_name") String cadre_category_name);

    long countById(Integer id);
}
