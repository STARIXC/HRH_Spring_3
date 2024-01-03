package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.dto.SubCountyDTO;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.repository.SubCountyRepository;
import org.utj.hrh.services.CountyNotFoundException;
import org.utj.hrh.services.CountyService;
import org.utj.hrh.services.EntityNotFoundException;
import org.utj.hrh.services.SubCountyService;

import java.util.List;

@Controller
@RequestMapping("/system/subCounty")
public class SubCountyController {
    private static final Logger logger = LoggerFactory.getLogger(SubCountyController.class);

    private final SubCountyRepository subCountyRepository;
 
    private final SubCountyService subCountyService;
 
    private final CountyService countyService;
    @Autowired
    public SubCountyController(SubCountyRepository subCountyRepository, SubCountyService subCountyService, CountyService countyService) {
        this.subCountyRepository = subCountyRepository;
        this.subCountyService = subCountyService;
        this.countyService = countyService;
    }
//        @GetMapping("/list/{countyId}")
//    public ResponseEntity<?> getSubCountyById(@PathVariable Integer countyId) throws EntityNotFoundException {
//
//        List<SubCounty> subCounties = subCountyService.getAllByCountyAndActive(countyId);
//        logger.info("subCounties: {}", subCounties);
//        if (subCounties.isEmpty()) {
//            // Return a 404 Not Found response when no data is found
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        // Return the data as JSON in the response body
//        return new ResponseEntity<>(subCounties, HttpStatus.OK);
//    }
@GetMapping("/list/{countyId}")
public ResponseEntity<?> getSubCountyById(@PathVariable Integer countyId) throws EntityNotFoundException {
    List<SubCountyDTO> subCountyDTOs = subCountyService.getAllByCountyAndActive(countyId);
    logger.info("subCountyDTOs: {}", subCountyDTOs);
    
    if (subCountyDTOs.isEmpty()) {
        // Return a 404 Not Found response when no data is found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Return the data as JSON in the response body
    return new ResponseEntity<>(subCountyDTOs, HttpStatus.OK);
}


}