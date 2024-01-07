package org.utj.hrh.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.utj.hrh.dto.EmployeeAcademicQualificationDTO;
import org.utj.hrh.model.EducationLevel;
import org.utj.hrh.model.License;
import org.utj.hrh.services.EducationLevelService;
import org.utj.hrh.services.EmployeeEducationQualificationService;
import org.utj.hrh.services.EntityNotFoundException;
import org.utj.hrh.services.LicenseService;

import java.util.List;

@Controller
@RequestMapping("/system/qualification")
public class QualificationController {
    private static final Logger logger = LoggerFactory.getLogger(QualificationController.class);
    private final EducationLevelService educationLevelService;
    private final LicenseService licenseService;
    private final EmployeeEducationQualificationService service;
    @Autowired
    public QualificationController(EducationLevelService educationLevelService, LicenseService licenseService, EmployeeEducationQualificationService service) {
        this.educationLevelService = educationLevelService;
        this.licenseService = licenseService;
        this.service = service;
    }

    @GetMapping("/education")
    public String viewEducation(@RequestParam(name = "id", required = false) Long educationID, Model model) {
//        // Check if roleId is provided; if not, it's a new role creation
        if (educationID == null) {
            // Create a new Role object for new role creation
            model.addAttribute("education", new EducationLevel());
        } else {
            // Retrieve the role from the database for editing
            EducationLevel educationLevel = educationLevelService.findEducationById(educationID);
            if (educationLevel == null) {
                // Handle the case where the role with the specified ID doesn't exist
                // You can redirect or display an error message here
                EducationLevel education_ = new EducationLevel();
                model.addAttribute("education", education_);
            } else {
                model.addAttribute("education", educationLevel);
            }
        }

        List<EducationLevel> e = educationLevelService.getAll();
        model.addAttribute("educations", e);
        model.addAttribute("pageTitle", "Education");
        return "pages/admin/qualification/education-qualification";
    }

    @PostMapping("/education/save")
    public String saveDesignation(EducationLevel educationLevel , RedirectAttributes redirectAttributes){
        educationLevelService.save(educationLevel);
        redirectAttributes.addFlashAttribute("message","Record added successfully");
        return "redirect:/system/qualification/education";
    }

    @GetMapping("/education/get/{educationId}")
    @ResponseBody
    public EducationLevel getEducationById(@PathVariable Long educationId) throws EntityNotFoundException {
        // Use your service to retrieve the document type by its ID
        EducationLevel educationLevel = educationLevelService.findEducationById(educationId);
        return educationLevel;
    }

    @DeleteMapping("/education/delete/{id}")
    public String deleteDocument(@PathVariable Long id) throws EntityNotFoundException {
        educationLevelService.delete(id);
        return "redirect:/system/qualification/education";
    }


    @GetMapping("/license")
    public String viewLicense(@RequestParam(name = "id", required = false) Long licenceID, Model model) {
//        // Check if roleId is provided; if not, it's a new role creation
        if (licenceID == null) {
            // Create a new Role object for new role creation
            model.addAttribute("license", new License());
        } else {
            // Retrieve the role from the database for editing
            License license = licenseService.findLicenseById(licenceID);
            if (license == null) {
                // Handle the case where the role with the specified ID doesn't exist
                // You can redirect or display an error message here
                License license1 = new License();
                model.addAttribute("license", license1);
            } else {
                model.addAttribute("license", license);
            }
        }

        List<License> licenses = licenseService.getAll();
        model.addAttribute("licenses", licenses);
        model.addAttribute("pageTitle", "License");
        return "pages/admin/qualification/license";
    }

    @PostMapping("/license/save")
    public String saveLicense(License license , RedirectAttributes redirectAttributes){
        licenseService.save(license);
        redirectAttributes.addFlashAttribute("message","Record added successfully");
        return "redirect:/system/qualification/license";
    }

    @GetMapping("/license/get/{licenseId}")
    @ResponseBody
    public License getLicenseById(@PathVariable Long licenseId) throws EntityNotFoundException {
        // Use your service to retrieve the document type by its ID
        License license = licenseService.findLicenseById(licenseId);
        return license;
    }

    @DeleteMapping("/license/delete/{id}")
    public String deleteLicense(@PathVariable Long id) throws EntityNotFoundException {
        licenseService.delete(id);
        return "redirect:/system/qualification/license";
    }
    
    @PostMapping("/employee/employee-education/save/{employeeId}")
    @ResponseBody
    public ResponseEntity<?> updateEducationQualificationInfo(@PathVariable Long employeeId,
                                                              @RequestBody EmployeeAcademicQualificationDTO EmployeeAcademicQualificationDTO) throws EntityNotFoundException {
        logger.info("Updating contact info for employee {}", EmployeeAcademicQualificationDTO);
        
        service.updateEducationQualificationInfo(employeeId, EmployeeAcademicQualificationDTO);
        return ResponseEntity.ok("Employee contact info updated successfully");
    }
}
