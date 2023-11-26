package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utj.hrh.model.Admin;
import org.utj.hrh.model.Facility;
import org.utj.hrh.model.Supervisor;

import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
//    Supervisor findByLoginId(String loginId); // Implement a method to fetch supervisor data by login ID
Optional<Supervisor> findByPersonPersonNumber(String personNumber); // Add this method

    Optional<Supervisor> findActiveSupervisorByFacilitySupervisorsAndStatus(Facility facility, Integer status);


}