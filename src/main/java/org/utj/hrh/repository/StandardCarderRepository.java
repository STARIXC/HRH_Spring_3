package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.StandardCarder;

@Repository
public interface StandardCarderRepository extends JpaRepository<StandardCarder,Integer> {
    @Query("SELECT s from StandardCarder s where s.standardized_cadre_name =:standardized_cadre_name ")
    public StandardCarder getStandardCarderByStandardized_cadre_name(@Param("standardized_cadre_name") String standardized_cadre_name);

    public long countById(Integer id);
}
