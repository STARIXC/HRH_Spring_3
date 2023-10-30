package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Designation;
import org.utj.hrh.model.EmployeePosition;
import org.utj.hrh.repository.DesignationRepository;
import org.utj.hrh.repository.EmployeePositionRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeePositionService {
    @Autowired
    private EmployeePositionRepository employeePositionRepository;
    public List<EmployeePosition> getAll(){
        return (List<EmployeePosition>) employeePositionRepository.findAll();
    }

    //    get carder type
    public EmployeePosition getDesignation(Integer id) throws DesignationNotFoundException {
        try {
            return employeePositionRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new DesignationNotFoundException("could not find any Position with ID :" + id);
        }
    }




}
