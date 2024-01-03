package org.utj.hrh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.model.Designation;
import org.utj.hrh.services.DesignationNotFoundException;

@Controller
@RequestMapping("/system/document")
public class DocumentController {



    @GetMapping("/manage")
    public String viewDesignations(Model model) {

//        List<StandardCarder> standardCarders = standardCarderService.getAll();
//        List<CarderCat> categories = carderCategoryService.getAll();
//        model.addAttribute("categories", categories);
//        model.addAttribute("standardCarders", standardCarders);
//        List<Designation> designations = designationService.getAll();
//        model.addAttribute("designations", designations);
//        model.addAttribute("pageTitle", "View :: Designations");
        return "pages/designations";
    }
//
//    @GetMapping("/new")
//    public String newDesignation(Model model) {
//        Designation designation = new Designation();
//        model.addAttribute("designation", designation);
//        List<StandardCarder> standardCarders = standardCarderService.getAll();
//        List<CarderCat> categories = carderCategoryService.getAll();
//        model.addAttribute("categories", categories);
//        model.addAttribute("standardCarders", standardCarders);
//        model.addAttribute("pageTitle", "Create :: New Position");
//        return "pages/designation-form";
//    }

    @PostMapping("/save")
    public String saveDesignation(Designation designation , RedirectAttributes redirectAttributes){
//        designationService.save(designation);
//        redirectAttributes.addFlashAttribute("message","Record added successfully");
        return "redirect:/system/designation/manage";
    }
    @GetMapping("/edit/{id}")
    public String editDesignation(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes){

//        try {
////            List<StandardCarder> standardCarders = standardCarderService.getAll();
////            List<CarderCat> categories = carderCategoryService.getAll();
////            model.addAttribute("categories", categories);
////            model.addAttribute("standardCarders", standardCarders);
////            Designation designation = designationService.getDesignation(id);
////            model.addAttribute("designation", designation);
////            model.addAttribute("pageTitle","Update ::  Standard Carder");
//            return "pages/designation-form";
//        } catch (DesignationNotFoundException e) {
//            redirectAttributes.addFlashAttribute("message",e.getMessage());
//            return "redirect:/system/designation/manage";
//        }
return  null;
    }

    @GetMapping("/delete/{id}")
    public String deleteDesignation(@PathVariable(name = "id") Integer id,Model model,RedirectAttributes redirectAttributes){
//        try {
////            designationService.delete(id);
////            redirectAttributes.addFlashAttribute("message","Record deleted successfully");
//
//        } catch (DesignationNotFoundException e) {
//            redirectAttributes.addFlashAttribute("message",e.getMessage());
//
//        }
        return "redirect:/system/document/manage";
    }

//    @GetMapping("/system/designation/get/{id}")
//    public String getDesignation(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes){
//
//        try {
//            List<StandardCarder> standardCarders = standardCarderService.getAll();
//            List<CarderCat> categories = carderCategoryService.getAll();
//            model.addAttribute("categories", categories);
//            model.addAttribute("standardCarders", standardCarders);
//            Designation designation = designationService.getDesignation(id);
//            model.addAttribute("designation", designation);
//            model.addAttribute("pageTitle","Update ::  Standard Carder");
//            return model;
//        } catch (DesignationNotFoundException e) {
//            redirectAttributes.addFlashAttribute("message",e.getMessage());
//            return "redirect:/system/designation/manage";
//        }
//
//    }

    @GetMapping("/system/document/get/{id}")
    public ResponseEntity<Designation> getDesignationById(@PathVariable Integer id) throws DesignationNotFoundException {
//        Designation designation = designationService.getDesignation(id);
//
//        if (designation != null) {
//            return new ResponseEntity<>(designation, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return null;
    }
}
