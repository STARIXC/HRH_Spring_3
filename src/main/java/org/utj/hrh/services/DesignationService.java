package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Designation;
import org.utj.hrh.model.Employee;
import org.utj.hrh.repository.DesignationRepository;

import java.util.*;

@Service
public class DesignationService {
    @Autowired
    private DesignationRepository designationRepository;
    public List<Designation> getAll(){
        return designationRepository.findAll();
    }
    public void save(Designation designation) {
        boolean isUpdatingDesignation = (designation.getId() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        designationRepository.save(designation);
    }
    //    get carder type
    public Designation getDesignation(Integer id) throws DesignationNotFoundException {
        try{
            return designationRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new DesignationNotFoundException("could not find any Position with ID :"+id);
        }

    }
    public void delete(Integer id) throws  DesignationNotFoundException {
        Long countById= designationRepository.countById(id);
        if (countById==null || countById==0){
            throw new DesignationNotFoundException("could not find any Position with ID :"+id);
        }
        designationRepository.deleteById(id);
    }

    public List<Designation> getByStandardCarder(Integer standardized_cadre_id) {
        return designationRepository.findPositionsByStandardized_cadre_id(standardized_cadre_id);
    }
    
    public Designation findActiveDesignationForEmployee(Integer position_id)  {
        return designationRepository.findById(position_id)
                .orElse(null);
               
    }
    
    public Designation findById(Integer designationId) {
        return designationRepository.findById(designationId).get();
    }
}
