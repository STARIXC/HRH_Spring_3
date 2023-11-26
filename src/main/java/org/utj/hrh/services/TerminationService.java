package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.DocumentType;
import org.utj.hrh.model.Termination;
import org.utj.hrh.repository.DocumentTypeRepository;
import org.utj.hrh.repository.TerminationsRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TerminationService {
    @Autowired
    private TerminationsRepository terminationsRepository;


    public List<Termination> getAll(){
        return terminationsRepository.findAll();
    }

    public void save(Termination termination) {
        boolean isUpdatingDocumentType = (termination.getTerminationId() !=null);
//        if (isUpdatingDocumentType){
//            DocumentType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        terminationsRepository.save(termination);
    }
    //    get carder type
    public Termination getTermination(Integer id) throws TerminationNotFoundException {
        try{
            return terminationsRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new TerminationNotFoundException("could not find any carder with ID :"+id);
        }

    }
    public void delete(Integer id) throws TerminationNotFoundException{
        Long countById= terminationsRepository.countByTerminationId(id);
        if (countById==null || countById==0){
            throw new TerminationNotFoundException("could not find any carder with ID :"+id);
        }
        terminationsRepository.deleteById(id);
    }
}
