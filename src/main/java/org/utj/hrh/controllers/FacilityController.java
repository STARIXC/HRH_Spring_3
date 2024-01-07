package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utj.hrh.dto.FacilityDTO;
import org.utj.hrh.model.Facility;
import org.utj.hrh.repository.FacilityRepository;
import org.utj.hrh.services.FacilityService;

import java.util.List;

@Controller
@RequestMapping("/system/facility")
public class FacilityController {
    private static final Logger logger = LoggerFactory.getLogger(FacilityController.class);
    private final FacilityRepository facilityRepository;
    private final FacilityService facilityService;
    @Autowired
    public FacilityController(FacilityRepository facilityRepository, FacilityService facilityService) {
        this.facilityRepository = facilityRepository;
        this.facilityService = facilityService;
    }
    
    
    @GetMapping("/list")
    public String viewSubCounty( Model model){


    List<Facility> facilities=facilityRepository.findAll();
    model.addAttribute("facilities",facilities);
    model.addAttribute("pageTitle", "Health Facilities");
    return "pages/facilities";
}

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getAllBySubCounty(@PathVariable Integer id) {
    Integer active =1;
        List<FacilityDTO> facilityDTOS = facilityService.getAllBySubCounty(id);
logger.info("facilities: {}", facilityDTOS);
        if (facilityDTOS.isEmpty()) {
            // Return a 404 Not Found response when no data is found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the data as JSON in the response body
        return new ResponseEntity<>(facilityDTOS, HttpStatus.OK);
    }

}
