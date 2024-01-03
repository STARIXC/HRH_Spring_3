package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.dto.LeaveEntitlementDTO;
import org.utj.hrh.dto.StaffDTO;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.FinancialYear;
import org.utj.hrh.model.LeaveEntitlement;
import org.utj.hrh.model.LeavePolicy;
import org.utj.hrh.services.EmployeeService;
import org.utj.hrh.services.FinancialYearService;
import org.utj.hrh.services.LeaveEntitlementService;
import org.utj.hrh.services.LeavePolicyService;

import java.util.List;

@Controller
@RequestMapping("/system/leave/entitlement")
public class LeaveEntitlementController {
    private final LeaveEntitlementService leaveEntitlementService;
    private final LeavePolicyService leavePolicyService;
    private final EmployeeService employeeService;
    private final FinancialYearService financialYearService;
    @Autowired
    public LeaveEntitlementController(LeaveEntitlementService leaveEntitlementService, LeavePolicyService leavePolicyService, EmployeeService employeeService, FinancialYearService financialYearService) {
        this.leaveEntitlementService = leaveEntitlementService;
        this.leavePolicyService = leavePolicyService;
        this.employeeService = employeeService;
        this.financialYearService = financialYearService;
    }
    
    @GetMapping("/all")
    public String viewLeaveEntitlement( Model model){
        List<LeaveEntitlementDTO> leaveEntitlementList=leaveEntitlementService.getAllLeaveEntitlements();
        model.addAttribute("leaveEntitlementList",leaveEntitlementList);
        model.addAttribute("pageTitle", "Leave Entitlement");
        return "pages/admin/leave/leave-entitlement";
    }
    @GetMapping("/myEntitlements")
    public String viewMyLeaveEntitlement( Model model, LeaveEntitlement leaveEntitlement){
//        leaveEntitlement=leaveEntitlementService.get(leaveEntitlement.getId());
//        List<LeaveEntitlement> leaveEntitlementList=leaveEntitlementService.getAll();
//        model.addAttribute("leaveEntitlementList",leaveEntitlementList);
        model.addAttribute("pageTitle", "Leave Entitlement");
        return "pages/admin/leave/my-leave-entitlement";
    }
    
    @GetMapping("/add")
    public String addLeaveEntitlement( Model model){
        LeaveEntitlement leaveEntitlement = new LeaveEntitlement();
        model.addAttribute("leaveEntitlement",leaveEntitlement);
        List<LeavePolicy> leavePolicies=leavePolicyService.getAll();
        model.addAttribute("leavePolicies",leavePolicies);
        List<FinancialYear> financialYears=financialYearService.getAllFY();
        model.addAttribute("financialYears",financialYears);
        List<StaffDTO> employees=employeeService.getAll();
        model.addAttribute("employees",employees);
        model.addAttribute("pageTitle", "Leave Entitlement");
        return "pages/admin/leave/leave-entitlement-form";
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveLeaveEntitlement(@RequestBody LeaveEntitlementDTO leaveEntitlementDTO) {
        try {
            if (leaveEntitlementDTO.getEmployeeLeaveEntitlement() != null) {
                // Individual employee
                leaveEntitlementService.saveIndividualLeaveEntitlement(leaveEntitlementDTO);
            } else {
                // All employees
                leaveEntitlementService.saveAllEmployeesLeaveEntitlement(leaveEntitlementDTO);
            }
            return ResponseEntity.ok("Leave entitlement saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving leave entitlement");
        }
    }
    @GetMapping("/{id}")
    public LeaveEntitlementDTO getById(@PathVariable Integer id) {
        return leaveEntitlementService.getLeaveEntitlementById(id);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        leaveEntitlementService.deleteLeaveEntitlement(id);
    }
    @GetMapping("/employee/{employeeId}")
    public List<LeaveEntitlementDTO> getLeaveEntitlementsByEmployeeId(@PathVariable Long employeeId) {
        return leaveEntitlementService.getLeaveEntitlementsByEmployeeId(employeeId);
    }
}
