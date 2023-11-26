package org.utj.hrh.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Facility;
import org.utj.hrh.model.Supervisor;
import org.utj.hrh.repository.SupervisorRepository;

import java.util.Optional;

@Service
public class SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    public Optional<Supervisor> getActiveSupervisorByFacility(Facility facility) {
        Integer status=1;
        return supervisorRepository.findActiveSupervisorByFacilitySupervisorsAndStatus(facility,status);
    }
}