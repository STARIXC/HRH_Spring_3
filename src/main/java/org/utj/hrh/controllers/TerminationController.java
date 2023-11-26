package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.model.*;
import org.utj.hrh.services.*;

import java.util.List;

@Controller
@RequestMapping("/system/terminations")
public class TerminationController {
    @Autowired
    private TerminationService terminationService;
    @Autowired
    private TerminationTypeService terminationTypeService;
@Autowired
private  EmployeeService employeeService;
    @GetMapping("/list")
    public String viewTerminations(Model model) {

        List<Termination> terminations = terminationService.getAll();
        Termination termination= new Termination();

        model.addAttribute("terminations", terminations);
        model.addAttribute("termination",termination);
        model.addAttribute("pageTitle", "Terminations");
        return "pages/admin/employee/termination/terminations";
    }
    @PostMapping("/save")
    public String saveTermination(@ModelAttribute("termination") Termination termination) {
        if (termination.getTerminationId() == null) {
            // It's a new role, so save it as a new record
            terminationService.save(termination);
        } else {
            // It's an edit, so update the existing role
            terminationService.save(termination);
        }
        return "redirect:/system/terminations/list"; // Redirect back to the same page
    }

    @GetMapping("/deleted/{id}")
    public String deleteTermination(@PathVariable Integer id) throws TerminationNotFoundException {
        // Delete the role with the specified ID
        terminationService.delete(id);
        return "redirect:/system/terminations/list"; // Redirect back to the same page
    }

    // Endpoint to fetch document type data by ID
    @GetMapping("/get/{termination_id}")
    @ResponseBody
    public Termination getTerminationById(@PathVariable Integer termination_id) throws TerminationNotFoundException {
        // Use your service to retrieve the document type by its ID
        Termination termination = terminationService.getTermination(termination_id);
        return termination;
    }
    @GetMapping("/form")
    public String showAddNewForm(Model model) {
        // Create operation, set the create flag
        model.addAttribute("createMode", true);
        List<TerminationType> terminationTypeList= terminationTypeService.getAll();
        model.addAttribute("terminationTypeList", terminationTypeList);
        Termination termination = new Termination();
        model.addAttribute("termination",termination);
        List<Employee> employeeList= employeeService.getAll();
        model.addAttribute("employeeList",employeeList);
        model.addAttribute("pageTitle", "Add Termination");

        return "pages/admin/employee/termination/add-termination"; // This corresponds to the Thymeleaf template
    }

}
