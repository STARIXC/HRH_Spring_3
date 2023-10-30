package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.model.County;
import org.utj.hrh.model.Role;
import org.utj.hrh.repository.CountyRepository;
import org.utj.hrh.repository.RoleRepository;

import java.util.List;

@Controller
@RequestMapping("/system/county")
public class CountyController {
@Autowired
    private CountyRepository countyRepository;


@GetMapping("/list")
    public String viewCounty( Model model){


    List<County> counties =countyRepository.findAll();
    model.addAttribute("counties",counties);
    model.addAttribute("pageTitle", "Roles");
    return "pages/roles";
}


}
