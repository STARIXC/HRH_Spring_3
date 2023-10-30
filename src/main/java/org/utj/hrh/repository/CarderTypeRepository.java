package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.CarderType;

@Repository
public interface CarderTypeRepository extends JpaRepository<CarderType,Integer> {
    @Query("SELECT c from CarderType c where c.cadre_type_name =:cadre_type_name ")
    public CarderType getCarderTypeByCadre_type_name(@Param("cadre_type_name") String cadre_type_name);

    public long countById(Integer id);
}
