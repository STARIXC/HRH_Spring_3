package org.utj.hrh.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.utj.hrh.model.CarderCat;
import org.utj.hrh.repository.CarderCategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarderCategoryService {
    @Autowired
    private CarderCategoryRepository carderCategoryDAO;
    public List<CarderCat> getAll(){
        return carderCategoryDAO.findAll();
    }

    public void save(CarderCat carderCategory) {
        boolean isUpdatingCarderCategory = (carderCategory.getId() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        carderCategoryDAO.save(carderCategory);
    }
    //    get carder type
    public CarderCat getCarderCategory(Integer id) throws CarderCategoryNotFoundException {
        try{
            return carderCategoryDAO.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new CarderCategoryNotFoundException("could not find any carder with ID :"+id);
        }

    }
    public void delete(Integer id) throws  CarderCategoryNotFoundException {
        Long countById= carderCategoryDAO.countById(id);
        if (countById==null || countById==0){
            throw new CarderCategoryNotFoundException("could not find any carder with ID :"+id);
        }
        carderCategoryDAO.deleteById(id);
    }
}

