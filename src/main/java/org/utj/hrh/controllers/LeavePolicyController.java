package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.model.*;
import org.utj.hrh.services.*;

import java.util.List;

@Controller
@RequestMapping("/system/leave/policy")
public class LeavePolicyController {

    private final LeavePolicyService leavePolicyService;
    private final FinancialYearService financialYearService;
    private final LeaveTypeService leaveTypeService;
    private final CarderTypeService carderTypeService;

    @Autowired
    public LeavePolicyController(LeavePolicyService leavePolicyService, FinancialYearService financialYearService, LeaveTypeService leaveTypeService, CarderTypeService carderTypeService) {
        this.leavePolicyService = leavePolicyService;
        this.financialYearService = financialYearService;
        this.leaveTypeService = leaveTypeService;
        this.carderTypeService = carderTypeService;
    }


    @GetMapping("/all")
    public String viewPolicies(Model model) {
        List<FinancialYear> financialYears=financialYearService.getAllFY();
        model.addAttribute("financialYears",financialYears);
        List<LeaveType> leaveTypes = leaveTypeService.getAll();
        model.addAttribute("leaveTypes", leaveTypes);
        LeavePolicy leavePolicy = new LeavePolicy();
        model.addAttribute("leavePolicy", leavePolicy);
        List<LeavePolicy> leavePolicies = leavePolicyService.getAll();
        List<CarderType> carderTypes = carderTypeService.getAll();
        model.addAttribute("carderTypes", carderTypes);
        model.addAttribute("leavePolicies", leavePolicies);
        model.addAttribute("pageTitle", "View :: LeaveType Policies");
        return "pages/admin/leave_policy/leave_policies";
    }

    @GetMapping("/form")
    public String showAddNewForm(Model model) {
        // Create operation, set the create flag
        model.addAttribute("createMode", true);
        LeavePolicy leavePolicy = new LeavePolicy();
        model.addAttribute("leavePolicy",leavePolicy);
        List<FinancialYear> financialYears=financialYearService.getAllFY();
        model.addAttribute("financialYears",financialYears);
        List<LeaveType> leaveTypes = leaveTypeService.getAll();
        model.addAttribute("leaveTypes", leaveTypes);

        return "pages/admin/leave_policy/leave_policy_form"; // This corresponds to the Thymeleaf template
    }

    @PostMapping("/save")
    public String saveLeavePolicy(LeavePolicy leavePolicy, RedirectAttributes redirectAttributes){
        leavePolicyService.save(leavePolicy);
        redirectAttributes.addFlashAttribute("message","The Policy was saved successfully");
        return "redirect:/system/leave/policy/all";
    }

    @GetMapping("/delete/{id}")
    public String deletePolicy(@PathVariable(name = "id") Integer id,Model model,RedirectAttributes redirectAttributes){
        try {
            leavePolicyService.delete(id);
            redirectAttributes.addFlashAttribute("message","Policy has been deleted successfully");

        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/system/leave/policy/all";
    }

}
