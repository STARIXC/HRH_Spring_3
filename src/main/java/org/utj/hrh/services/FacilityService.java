package org.utj.hrh.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.FacilityDTO;
import org.utj.hrh.model.County;
import org.utj.hrh.model.Facility;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.repository.FacilityRepository;
import org.utj.hrh.repository.SubCountyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityService {
    private final FacilityRepository facilityRepository;
    private final SubCountyRepository subCountyRepository;
    private final  ModelMapper modelMapper;
    
    @Autowired
    public FacilityService(FacilityRepository facilityRepository, SubCountyRepository subCountyRepository, ModelMapper modelMapper) {
        this.facilityRepository = facilityRepository;
        this.subCountyRepository = subCountyRepository;
        this.modelMapper = modelMapper;
    }
    
    public List<Facility> getAll(){
        return facilityRepository.findAll();
    }

    public List<FacilityDTO> getAllBySubCounty(Integer districtID) {
        SubCounty subcounty = subCountyRepository.findById(districtID)
                .orElseThrow(() -> new RuntimeException("SubCounty not found"));
    
        List<Facility> facilities = facilityRepository.findBySubCountyAndActive(subcounty, 1);
        return facilities.stream()
                .map(this::convertToEmployeeFacilityDTO)
                .collect(Collectors.toList());

    }

    public String getFacility(Integer centersanteID){
        return  facilityRepository.findByMFL(centersanteID);
    }
    
    public FacilityDTO convertToEmployeeFacilityDTO(Facility facility) {
        FacilityDTO dto = new FacilityDTO();
        dto.setSubPartnerId(facility.getSubPartnerId());
        dto.setSubPartnerName(facility.getSubPartnerName());
        
        SubCounty subCounty = facility.getSubCounty();
        if (subCounty != null) {
            dto.setDistrictId(subCounty.getDistrictId());
            dto.setDistrictName(subCounty.getDistrictName());
            County county = subCounty.getCounty();
            if (county != null) {
                dto.setCountyName(county.getCountyName());
            }
        }
        
        return dto;
    }
    
    public Facility getFacilityById(Integer facilityId) {
        return facilityRepository.findById(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found"));
    }
}
