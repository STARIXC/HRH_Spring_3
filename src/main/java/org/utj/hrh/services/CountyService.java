package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.CarderType;
import org.utj.hrh.model.County;
import org.utj.hrh.repository.CarderTypeRepository;
import org.utj.hrh.repository.CountyRepository;
import org.utj.hrh.repository.SubCountyRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CountyService {
    @Autowired
    private CountyRepository countyRepository;


    @Autowired
    private SubCountyRepository subCountyRepository;


    public List<County> getAll(){
        return (List<County>) countyRepository.findAll();
    }
    public List<County> getActive(){
        Integer active=1;
        return (List<County>) countyRepository.findByActive(active);
    }

    public void save(County county) {
        boolean isUpdatingCounty = (county.getCounty_id() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        countyRepository.save(county);
    }
    //    get carder type
    public County getCounty(Integer id) throws CountyNotFoundException {
        try{
            return countyRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new CountyNotFoundException("could not find any County with ID :"+id);
        }

    }

}
