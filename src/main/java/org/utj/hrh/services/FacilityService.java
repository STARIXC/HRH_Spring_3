package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Facility;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.repository.FacilityRepository;
import org.utj.hrh.repository.SubCountyRepository;

import java.util.List;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepository facilityRepository;


    public List<Facility> getAll(){
        return (List<Facility>) facilityRepository.findAll();
    }
//    public List<Facility> getActive(){
//        Integer active=1;
//        return (List<Facility>) facilityRepository.findByActive(active);
//    }

    public List<Facility> getAllBySubCounty(Integer districtID) {

        return facilityRepository.fetchDataFromDataSource(districtID);
    }

    public String getFacility(Integer centersanteID){
        return  facilityRepository.findByMFL(centersanteID);
    }

}
