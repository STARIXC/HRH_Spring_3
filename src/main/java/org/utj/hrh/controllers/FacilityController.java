package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utj.hrh.model.Facility;
import org.utj.hrh.model.SubCounty;
import org.utj.hrh.repository.FacilityRepository;
import org.utj.hrh.repository.SubCountyRepository;
import org.utj.hrh.services.FacilityService;
import org.utj.hrh.services.SubCountyService;

import java.util.List;

@Controller
@RequestMapping("/system/facility")
public class FacilityController {
@Autowired
    private FacilityRepository facilityRepository;
@Autowired
private FacilityService facilityService;


@GetMapping("/list")
    public String viewSubCounty( Model model){


    List<Facility> facilities=facilityRepository.findAll();
    model.addAttribute("facilities",facilities);
    model.addAttribute("pageTitle", "Health Facilities");
    return "pages/facilities";
}

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getAllBySubcounty(@PathVariable Integer id) {
    Integer active =1;
        List<Facility> facilities = facilityService.getAllBySubCounty(id);

        if (facilities.isEmpty()) {
            // Return a 404 Not Found response when no data is found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the data as JSON in the response body
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }

}
