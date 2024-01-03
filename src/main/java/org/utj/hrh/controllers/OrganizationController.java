package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utj.hrh.model.Country;
import org.utj.hrh.model.Organization;
import org.utj.hrh.repository.EmployeeRepository;
import org.utj.hrh.services.CountryService;
import org.utj.hrh.services.EntityNotFoundException;
import org.utj.hrh.services.OrganizationService;

import java.util.List;

@Controller
@RequestMapping("/system/organization")
public class OrganizationController {


    private final OrganizationService organizationService;
    private final EmployeeRepository employeeRepository;

    private final CountryService countryService;

    @Autowired
    public OrganizationController(OrganizationService organizationService, EmployeeRepository employeeRepository, CountryService countryService) {
        this.organizationService = organizationService;
        this.employeeRepository = employeeRepository;
        this.countryService = countryService;
    }


    @GetMapping("/view")
    public String viewOrganization(Model model) {

        model.addAttribute("organizationEditable", false); // or false depending on your logic
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countryList", countryList);

        Integer totalEmployees = Math.toIntExact(employeeRepository.count());
        model.addAttribute("totalEmployees", totalEmployees);

        Organization organization = organizationService.getOrganization(); // Retrieve the organization record
        System.out.println(organization);
        model.addAttribute("organization", organization);
        model.addAttribute("pageTitle", "Organization Info");

        return "pages/admin/Administration/organization";
    }
    @PostMapping("/info/save")
    public String saveOrganization(@ModelAttribute Organization organization) {
        organizationService.saveOrganization(organization); // Save the updated organization information
        return "redirect:/system/organization/view";
    }

}
