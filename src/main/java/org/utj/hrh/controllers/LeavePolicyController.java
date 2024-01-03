package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.dto.LeavePolicyDetailsDTO;
import org.utj.hrh.dto.StaffDTO;
import org.utj.hrh.model.*;
import org.utj.hrh.services.*;

import java.util.List;

@Controller
@RequestMapping("/system/leave/policy")
public class LeavePolicyController {

    private final LeavePolicyService leavePolicyService;
    private final FinancialYearService financialYearService;
    private final LeaveTypeService leaveTypeService;
    private final CarderCategoryService carderCategoryService;
    private final EmployeeService employeeService;

    @Autowired
    public LeavePolicyController(LeavePolicyService leavePolicyService, FinancialYearService financialYearService, LeaveTypeService leaveTypeService, CarderCategoryService carderCategoryService, EmployeeService employeeService) {
        this.leavePolicyService = leavePolicyService;
        this.financialYearService = financialYearService;
        this.leaveTypeService = leaveTypeService;
        this.carderCategoryService = carderCategoryService;
    
        this.employeeService = employeeService;
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
        List<CarderCategory> carderCategories = carderCategoryService.getAll();
        model.addAttribute("carderCategories", carderCategories);
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
    public String deletePolicy(@PathVariable(name = "id") Long id,Model model,RedirectAttributes redirectAttributes){
        try {
            leavePolicyService.delete(id);
            redirectAttributes.addFlashAttribute("message","Policy has been deleted successfully");

        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/system/leave/policy/all";
    }
    
    @GetMapping("/getList")
    public List<LeavePolicy> getPoliciesForYear(@RequestParam("year") Integer year) {
        return leavePolicyService.getPoliciesForFinancialYear(year);
    }
    @GetMapping("/getPoliciesForYear")
    public ResponseEntity<?> getListByYear(@RequestParam("year") Integer year) {
        List<LeavePolicy> leavePolicies = leavePolicyService.getPoliciesForFinancialYear(year);
        
        if (leavePolicies.isEmpty()) {
            // Return a 404 Not Found response when no data is found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Return the data as JSON in the response body
        return new ResponseEntity<>(leavePolicies, HttpStatus.OK);
    }
    
    @GetMapping("/details/{policyId}")
    public ResponseEntity<LeavePolicyDetailsDTO> getPolicyDetails(@PathVariable Long policyId) throws EntityNotFoundException {
        LeavePolicyDetailsDTO policyDetails = leavePolicyService.getPolicyDetails(policyId);
        return ResponseEntity.ok(policyDetails);
    }
    
    
    @GetMapping("/filtered-employees/{gender}")
    public ResponseEntity<List<StaffDTO>> getEmployeesByGender(@PathVariable String gender) {
        List<StaffDTO> filteredEmployees = employeeService.getByGender(gender);
        return ResponseEntity.ok(filteredEmployees);
    }
    
    
}
