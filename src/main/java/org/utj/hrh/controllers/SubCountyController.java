package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.model.County;
import org.utj.hrh.model.StandardCarder;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.repository.CountyRepository;
import org.utj.hrh.repository.SubCountyRepository;
import org.utj.hrh.services.CountyNotFoundException;
import org.utj.hrh.services.CountyService;
import org.utj.hrh.services.SubCountyService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/system/subcounty")
public class SubCountyController {
    @Autowired
    private SubCountyRepository subCountyRepository;
    @Autowired
    private SubCountyService subCountyService;
    @Autowired
    private CountyService countyService;

    @GetMapping("/list")
    public String viewSubCounty(Model model) {


        List<SubCounty> subCounties = subCountyRepository.findAll();
        model.addAttribute("subCounties", subCounties);
        model.addAttribute("pageTitle", "SubCounties");
        return "pages/subcounties";
    }

        @GetMapping("/list/{id}")
    public ResponseEntity<?> getSubCountyById(@PathVariable Integer id) throws CountyNotFoundException {
    Integer active_ =1;
//            County county = countyService.getCounty(id);
        List<SubCounty> subCounties = subCountyService.getAllByCountyAndActive(id,active_);

        if (subCounties.isEmpty()) {
            // Return a 404 Not Found response when no data is found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the data as JSON in the response body
        return new ResponseEntity<>(subCounties, HttpStatus.OK);
    }
//    @GetMapping("/regions/{id}")
//    @ResponseBody
//    public ResponseEntity<?> getDistrictForCounty(@PathVariable("id") Integer countyId) throws CountyNotFoundException {
//        County county = countyService.getCounty(countyId);
//        if (county != null) {
//            Integer active = 1;
//            List<SubCounty> subCounties = subCountyService.getAllByCounty(county, active);
////        return subCountyService.getAllByCounty(county,active);
//            if (subCounties.isEmpty()) {
//                // Return a 404 Not Found response when no data is found
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(subCounties, HttpStatus.OK);
//        }
//
//        // Return a 404 Not Found response when no data is found
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}