package org.utj.hrh.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.model.CarderCat;
import org.utj.hrh.model.CarderType;
import org.utj.hrh.model.StandardCarder;
import org.utj.hrh.services.*;

import java.util.List;

@Controller
public class StandardCarderController {

    @Autowired
    private StandardCarderService standardCarderService;
    @Autowired
    private CarderCategoryService carderCategoryService;

    @Autowired
    CarderTypeService carderTypeService;

    @GetMapping("/system/manage/standardised_carder")
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
        List<CarderType> carderTypes = carderTypeService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("carderTypes", carderTypes);
        model.addAttribute("pageTitle", "Create :: New Standard Carder");
        return "pages/standard-carder-form";
    }

    @PostMapping("/system/standard/carder/save")
    public String saveStandardCarder(StandardCarder standardCarder , RedirectAttributes redirectAttributes){
        standardCarderService.save(standardCarder);
        redirectAttributes.addFlashAttribute("message","Record added successfully");
        return "redirect:/system/manage/standardised_carder";
    }
    @GetMapping("/system/standard_carder/edit/{id}")
    public String editStandardCarder(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes){

        try {
            List<CarderCat> categories = carderCategoryService.getAll();
            List<CarderType> carderTypes = carderTypeService.getAll();
            model.addAttribute("categories", categories);
            model.addAttribute("carderTypes", carderTypes);
            StandardCarder standardCarder = standardCarderService.getStandardCarder(id);
            model.addAttribute("standardCarder", standardCarder);
            model.addAttribute("pageTitle","Update ::  Standard Carder");
            return "pages/standard-carder-form";
        } catch (StandardCarderNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/system/manage/standardised_carder";
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
        return "redirect:/system/manage/standardised_carder";
    }

    @GetMapping("/system/standard_carder/{id}")
    public ResponseEntity<?> getStandardCategoryByCategoryType(@PathVariable Integer id) {
        List<StandardCarder> standardCarders = standardCarderService.fetchDataFromDataSource(id);

        if (standardCarders.isEmpty()) {
            // Return a 404 Not Found response when no data is found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the data as JSON in the response body
        return new ResponseEntity<>(standardCarders, HttpStatus.OK);
    }

    @GetMapping("/system/standard_carder/list/{id}")
    public ResponseEntity<?> getListByCarderType(@PathVariable Integer id) {
        List<StandardCarder> standardCarders = standardCarderService.getByCarderType(id);

        if (standardCarders.isEmpty()) {
            // Return a 404 Not Found response when no data is found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the data as JSON in the response body
        return new ResponseEntity<>(standardCarders, HttpStatus.OK);
    }

//    @GetMapping("/system/standard_carder/{id}")
//     public List<StandardCarder> getStandardCategoryByCategoryType(Integer id) throws StandardCarderNotFoundException {
//        // Replace this with actual data retrieval logic from your data source
//        List<StandardCarder> standardCarders = standardCarderService.fetchDataFromDataSource(id);
//        if (standardCarders.isEmpty()) {
//            // Return a 404 Not Found response when no data is found
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        // Return the data as JSON in the response body
//        return new ResponseEntity<>(standardCarders, HttpStatus.OK);
//    }

}
