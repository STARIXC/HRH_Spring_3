package org.utj.hrh.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.model.CarderCat;
import org.utj.hrh.model.StandardCarder;
import org.utj.hrh.services.CarderCategoryService;
import org.utj.hrh.services.StandardCarderNotFoundException;
import org.utj.hrh.services.StandardCarderService;

import java.util.List;

@Controller
public class StandardCarderController {

    @Autowired
    private StandardCarderService standardCarderService;
    @Autowired
    private CarderCategoryService carderCategoryService;


    @GetMapping("/system/manage_standardised_carder")
    public String viewStandardCarder(Model model) {
        List<StandardCarder> standardCarderList = standardCarderService.getAll();
        model.addAttribute("standardCarderList", standardCarderList);
        model.addAttribute("pageTitle", "Standard Carders");
        return "pages/standard-carder";
    }

    @GetMapping("/system/standard_carder/new")
    public String newStandardCarder(Model model) {
        StandardCarder standardCarder = new StandardCarder();
        model.addAttribute("standardCarder", standardCarder);
        List<CarderCat> categories = carderCategoryService.getAll();

        model.addAttribute("categories", categories);

        model.addAttribute("pageTitle", "Create :: New Carder Category");
        return "pages/standardized_carder_form";
    }

    @PostMapping("/system/standard/carder/save")
    public String saveStandardCarder(StandardCarder standardCarder , RedirectAttributes redirectAttributes){
        standardCarderService.save(standardCarder);
        redirectAttributes.addFlashAttribute("message","Record added successfully");
        return "redirect:/system/manage_standardised_carder";
    }
    @GetMapping("/system/standard_carder/edit/{id}")
    public String editStandardCarder(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes){

        try {
            List<CarderCat> categories = carderCategoryService.getAll();
//            List<CarderType> carderTypes = carderTypeService.getAll();
            model.addAttribute("categories", categories);
//            model.addAttribute("carderTypes", carderTypes);
            StandardCarder standardCarder = standardCarderService.getStandardCarder(id);
            model.addAttribute("standardCarder", standardCarder);
            model.addAttribute("pageTitle","Update ::  Standard Carder");
            return "pages/standardized_carder_form";
        } catch (StandardCarderNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/system/manage_standardised_carder";
        }

    }

    @GetMapping("/system/standard_carder/delete/{id}")
    public String deleteStandardCarder(@PathVariable(name = "id") Integer id,Model model,RedirectAttributes redirectAttributes){
        try {
            standardCarderService.delete(id);
            redirectAttributes.addFlashAttribute("message","Record deleted successfully");

        } catch (StandardCarderNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/system/manage_standardised_carder";
    }
}
