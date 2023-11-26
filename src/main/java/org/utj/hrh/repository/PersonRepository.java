package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findPersonByPersonNumber(String personNumber);

    Person findByPersonNumber(String personNumber);
}