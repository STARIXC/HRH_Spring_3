package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.SubCountyDTO;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.repository.SubCountyRepository;

import java.util.List;
import java.util.stream.Collectors;

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

public List<SubCountyDTO> getAllByCountyAndActive(Integer countyId) {
    List<SubCounty> subCounties = subCountyRepository.findByCountyCountyIdAndActive(countyId, 1);
    
    return subCounties.stream().map(subCounty -> {
        SubCountyDTO dto = new SubCountyDTO();
        dto.setDistrictId(subCounty.getDistrictId());
        dto.setDistrictName(subCounty.getDistrictName());
        dto.setCountyId(subCounty.getCounty().getCountyId());
        return dto;
    }).collect(Collectors.toList());

}

}
