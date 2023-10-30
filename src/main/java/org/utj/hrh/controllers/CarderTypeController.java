package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.model.CarderType;
import org.utj.hrh.services.CarderTypeNotFoundException;
import org.utj.hrh.services.CarderTypeService;

import java.util.List;

@Controller
@RequestMapping("/system/carderType")
public class CarderTypeController {
    @Autowired
    private CarderTypeService carderTypeService;


    @GetMapping("/manage/all")
    public String viewCarderTypes(Model model) {

        List<CarderType> types = carderTypeService.getAll();
        model.addAttribute("carder_types", types);
        model.addAttribute("pageTitle", "View :: Carder Type");
        return "carderTypes";
    }

    @GetMapping("/new")
    public String newCarderType(Model model) {
        CarderType carderType = new CarderType();
        model.addAttribute("carderType", carderType);
        model.addAttribute("pageTitle", "Create :: New Carder Type");
        return "carderType_form";
    }

    @PostMapping("/save")
    public String saveCarderType(CarderType carderType, RedirectAttributes redirectAttributes){
        carderTypeService.save(carderType);
        redirectAttributes.addFlashAttribute("message","The user was saved successfully");
        return "redirect:/manage_carder_type";
    }
    @GetMapping("/carder_type/edit/{id}")
    public String editCarder(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes){

        try {
            CarderType carderType = carderTypeService.getCarderType(id);
            model.addAttribute("carderType", carderType);
            model.addAttribute("pageTitle","Update ::  Carder_Type");
            return "carderType_form";
        } catch (CarderTypeNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/manage_carder_type";
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteCarderType(@PathVariable(name = "id") Integer id,Model model,RedirectAttributes redirectAttributes){
        try {
            carderTypeService.delete(id);;
            redirectAttributes.addFlashAttribute("message","User has been deleted successfully");

        } catch (CarderTypeNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/manage_carder_type";
    }

}
