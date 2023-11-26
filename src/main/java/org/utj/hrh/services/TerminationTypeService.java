package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.CarderType;
import org.utj.hrh.model.TerminationType;
import org.utj.hrh.repository.CarderTypeRepository;
import org.utj.hrh.repository.TerminationTypeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TerminationTypeService {
    @Autowired
    private TerminationTypeRepository terminationTypeRepository;


    public List<TerminationType> getAll(){

        return terminationTypeRepository.findAll();
    }

    public void save(TerminationType terminationType) {
        boolean isUpdatingCarderType = (terminationType.getId() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        terminationTypeRepository.save(terminationType);
    }
    //    get carder type
    public TerminationType getTerminationType(Integer id) throws TerminationTypeNotFoundException {
        try{
            return terminationTypeRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new TerminationTypeNotFoundException("could not find any TerminationType with ID :"+id);
        }

    }
    public void delete(Integer id) throws TerminationTypeNotFoundException{
        Long countById= terminationTypeRepository.countById(id);
        if (countById==null || countById==0){
            throw new TerminationTypeNotFoundException("could not find any carder with ID :"+id);
        }
        terminationTypeRepository.deleteById(id);
    }
}
