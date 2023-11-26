package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.EmployeeStatus;
import org.utj.hrh.model.Facility;
import org.utj.hrh.repository.EmployeeStatusRepository;
import org.utj.hrh.repository.FacilityRepository;

import java.util.List;

@Service
public class EmployeeStatusService {
    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;


    public List<EmployeeStatus> getAll(){
        return employeeStatusRepository.findAll();
    }

}
