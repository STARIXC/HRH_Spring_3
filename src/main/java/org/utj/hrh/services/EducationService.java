package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.utj.hrh.model.EducationLevel;
import org.utj.hrh.repository.EducationRepository;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    @Autowired
    public EducationService( EducationRepository educationRepository) {
        this.educationRepository = educationRepository;

    }
    public EducationLevel getEducationByName(String name){
        return educationRepository.findByName(name);
    }
    public List<EducationLevel> getAll() {
        return educationRepository.findAll();
    }

    public EducationLevel findEducationById(Long id) {
        return educationRepository.findById(id).orElse(null);
    }

    public void save(EducationLevel educationLevel) {
        boolean isUpdatingEducation = (educationLevel.getId() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        educationRepository.save(educationLevel);
    }

    public void delete(Long id) throws EntityNotFoundException{
        Long countById= educationRepository.countById(id);
        if (countById==null || countById==0){
            throw new EntityNotFoundException("could not find any Record with ID :"+id);
        }
        educationRepository.deleteById(id);
    }
    
}
