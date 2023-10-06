package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.CarderCat;

@Repository
public interface CarderCategoryRepository extends JpaRepository<CarderCat,Integer> {
    @Query("SELECT c from CarderCat c where c.cadre_category_name =:cadre_category_name ")
    public CarderCat getCarderTypeByCadre_category_name(@Param("cadre_category_name") String cadre_category_name);

    public long countById(Integer id);
}
