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
import org.utj.hrh.model.LeavePolicy;
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

    @GetMapping("/list/{countyId}")
    @ResponseBody
    public ResponseEntity<List<SubCountyDTO>> getActiveSubCountiesByCounty(@PathVariable Integer countyId) {
        List<SubCountyDTO> activeSubCounties = subCountyService.getAllByCountyAndActive(countyId);
    
        if(activeSubCounties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        return ResponseEntity.ok(activeSubCounties);
    }
    
    
}