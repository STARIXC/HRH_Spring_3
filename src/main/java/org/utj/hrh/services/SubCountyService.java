package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.County;
import org.utj.hrh.model.StandardCarder;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.repository.CountyRepository;
import org.utj.hrh.repository.SubCountyRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubCountyService {
    @Autowired
    private SubCountyRepository subCountyRepository;


    public List<SubCounty> getAll(){
        return (List<SubCounty>) subCountyRepository.findAll();
    }
    public List<SubCounty> getActive(){
        Integer active=1;
        return (List<SubCounty>) subCountyRepository.findByActive(active);
    }

//    public List<SubCounty> getAllByCounty(County county,Integer active_) {
//
//        return subCountyRepository.findByCountyAndActive(county ,active_);
//    }
    public List<SubCounty> getAllByCountyAndActive(Integer county_id,Integer active_) {

        return subCountyRepository.fetchDataFromDataSource( county_id,active_);
    }


}
