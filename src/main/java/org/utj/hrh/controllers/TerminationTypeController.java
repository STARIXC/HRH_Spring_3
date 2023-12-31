package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.model.TerminationType;
import org.utj.hrh.services.TerminationTypeNotFoundException;
import org.utj.hrh.services.TerminationTypeService;

import java.util.List;

@Controller
@RequestMapping("/system/termination/types")
public class TerminationTypeController {

    private final TerminationTypeService terminationTypeService;

    @Autowired
    public TerminationTypeController(TerminationTypeService terminationTypeService) {
        this.terminationTypeService = terminationTypeService;
    }


    @GetMapping("/manage/all")
    public String viewCarderTypes(Model model) {
        TerminationType terminationType = new TerminationType();
        model.addAttribute("terminationType", terminationType);
        List<TerminationType> types = terminationTypeService.getAll();
        model.addAttribute("termination_types", types);
        model.addAttribute("pageTitle", "View :: Termination Type");
        return "pages/admin/Administration/terminations";
    }


    @PostMapping("/save")
    public String saveTerminationType(TerminationType terminationType, RedirectAttributes redirectAttributes){
        terminationTypeService.save(terminationType);
        redirectAttributes.addFlashAttribute("message","The Type was saved successfully");
        return "redirect:/system/termination/types/manage/all";
    }
//    @GetMapping("/carder_type/edit/{id}")
//    public String editCarder(@PathVariable(name="id") Integer id, Model model, RedirectAttributes redirectAttributes){
//
//        try {
//            CarderType carderType = carderTypeService.getCarderType(id);
//            model.addAttribute("carderType", carderType);
//            model.addAttribute("pageTitle","Update ::  Carder_Type");
//            return "carderType_form";
//        } catch (CarderTypeNotFoundException e) {
//            redirectAttributes.addFlashAttribute("message",e.getMessage());
//            return "redirect:/manage_carder_type";
//        }
//
//    }

    @GetMapping("/delete/{id}")
    public String deleteTerminationType(@PathVariable(name = "id") Integer id,Model model,RedirectAttributes redirectAttributes){
        try {
            terminationTypeService.delete(id);
            redirectAttributes.addFlashAttribute("message","User has been deleted successfully");

        } catch (TerminationTypeNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/manage_carder_type";
    }

}
