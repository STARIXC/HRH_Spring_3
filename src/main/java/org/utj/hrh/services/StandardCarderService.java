package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.StandardCarder;
import org.utj.hrh.repository.StandardCarderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StandardCarderService {

    @Autowired
    private StandardCarderRepository standardCarderRepository;
    public List<StandardCarder> getAll(){
        return standardCarderRepository.findAll();
    }


    public void save(StandardCarder standardCarder) {
//        boolean isUpdatingStandardCarder = (standardCarder.getId() !=null);

        standardCarderRepository.save(standardCarder);
    }



    //    get carder type
    public StandardCarder getStandardCarder(Integer id) throws StandardCarderNotFoundException {
        try{
            return standardCarderRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new StandardCarderNotFoundException("could not find any carder with ID :"+id);
        }

    }

    public void delete(Integer id) throws  StandardCarderNotFoundException {
        Long countById= standardCarderRepository.countById(id);
        if (countById==null || countById==0){
            throw new StandardCarderNotFoundException("could not find any carder with ID :"+id);
        }
        standardCarderRepository.deleteById(id);
    }
}
