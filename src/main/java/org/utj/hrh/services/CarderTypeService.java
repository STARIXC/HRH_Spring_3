package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.CarderType;
import org.utj.hrh.repository.CarderTypeRepository;

import java.util.*;

@Service
public class CarderTypeService {
    @Autowired
    private CarderTypeRepository carderTypeRepository;


    public List<CarderType> getAll(){
        return (List<CarderType>) carderTypeRepository.findAll();
    }

    public void save(CarderType carderType) {
        boolean isUpdatingCarderType = (carderType.getId() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        carderTypeRepository.save(carderType);
    }
    //    get carder type
    public CarderType getCarderType(Integer id) throws CarderTypeNotFoundException {
        try{
            return carderTypeRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new CarderTypeNotFoundException("could not find any carder with ID :"+id);
        }

    }
    public void delete(Integer id) throws CarderTypeNotFoundException{
        Long countById= carderTypeRepository.countById(id);
        if (countById==null || countById==0){
            throw new CarderTypeNotFoundException("could not find any carder with ID :"+id);
        }
        carderTypeRepository.deleteById(id);
    }
}
