package org.utj.hrh.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.model.CarderCategory;
import org.utj.hrh.services.*;

import java.util.List;

@Controller
public class CarderCategoryController {

   
    private final  CarderCategoryService carderCategoryService;
    @Autowired
    public CarderCategoryController( CarderCategoryService carderCategoryService) {
       
        this.carderCategoryService = carderCategoryService;
    }
    
    
    @GetMapping("/system/manage/carderCategories")
    public String viewCarderCategory(Model model) {
        List<CarderCategory> categories = carderCategoryService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "Carder Categories");
        return "pages/admin/Administration/carder-category";
    }

    @GetMapping("/system/carder_category/new")
    public String newCarderCategory(Model model) {
        CarderCategory carderCategory = new CarderCategory();
        model.addAttribute("carderCategory", carderCategory);
        model.addAttribute("pageTitle", "Create :: New Carder Category");
        return "pages/admin/Administration/carder-category-form";
    }

    @PostMapping("/system/carder/category/save")
    public String saveCarderCategory(CarderCategory carderCategory , RedirectAttributes redirectAttributes){
        carderCategoryService.save(carderCategory);
        redirectAttributes.addFlashAttribute("message","Record added successfully");
        return "redirect:/system/manage/carderCategories";
    }
    @GetMapping("/system/carder_category/edit/{id}")
    public String editCarderCategory(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes){

        try {
            CarderCategory carderCategory = carderCategoryService.getCarderCategory(id);
            model.addAttribute("carderCategory", carderCategory);
            model.addAttribute("pageTitle","Update ::  Standard Carder");
            return "pages/admin/Administration/carder-category-form";
        } catch (CarderCategoryNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/system/manage/carderCategories";
        }

    }

    @GetMapping("/system/carder_category/delete/{id}")
    public String deleteCarderCategory(@PathVariable(name = "id") Integer id,Model model,RedirectAttributes redirectAttributes){
        try {
            carderCategoryService.delete(id);
            redirectAttributes.addFlashAttribute("message","Record deleted successfully");

        } catch (CarderCategoryNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/system/manage/carderCategories";
    }


}
