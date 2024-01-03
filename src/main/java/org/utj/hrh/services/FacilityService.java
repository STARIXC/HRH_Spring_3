package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.FacilityDTO;
import org.utj.hrh.model.Facility;
import org.utj.hrh.repository.FacilityRepository;

import java.util.List;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepository facilityRepository;


    public List<Facility> getAll(){
        return facilityRepository.findAll();
    }
//    public List<Facility> getActive(){
//        Integer active=1;
//        return (List<Facility>) facilityRepository.findByActive(active);
//    }

    public List<FacilityDTO> getAllBySubCounty(Integer districtID) {

        return facilityRepository.fetchDataFromDataSource(districtID);
    }

    public String getFacility(Integer centersanteID){
        return  facilityRepository.findByMFL(centersanteID);
    }

}
