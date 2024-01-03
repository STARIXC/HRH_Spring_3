package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.SubCountyDTO;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.repository.SubCountyRepository;

import java.util.List;

@Service
public class SubCountyService {
    private final SubCountyRepository subCountyRepository;
    
    @Autowired
    public SubCountyService(SubCountyRepository subCountyRepository) {
        this.subCountyRepository = subCountyRepository;
    }
    
    public List<SubCounty> getAll(){
        return subCountyRepository.findAll();
    }
    public List<SubCounty> getActive(){
        Integer active=1;
        return subCountyRepository.findByActive(active);
    }

//    public List<SubCounty> getAllByCounty(County county,Integer active_) {
//
//        return subCountyRepository.findByCountyAndActive(county ,active_);
//    }
//    public List<SubCounty> getAllByCountyAndActive(Integer county_id) {
//
//        return subCountyRepository.fetchDataFromDataSource(county_id);
//    }
public List<SubCountyDTO> getAllByCountyAndActive(Integer countyId) {
    return subCountyRepository.fetchDataFromDataSource(countyId);
}

}
