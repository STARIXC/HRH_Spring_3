package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Country;

import java.util.Optional;


@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
    Country findByName(String name);

    Long countByCountryCode(String cou_code);


}
