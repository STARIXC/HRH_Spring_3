package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.dto.LeaveRequestDTO;
import org.utj.hrh.model.LeavePolicy;
import org.utj.hrh.services.LeavePolicyService;
import org.utj.hrh.services.LeaveRequestService;

import java.util.List;

@Controller
@RequestMapping("/system/leave/application")
public class LeaveController {
	private final LeavePolicyService leavePolicyService;
	private final LeaveRequestService leaveRequestService;
	@Autowired
	public LeaveController(LeavePolicyService leavePolicyService, LeaveRequestService leaveRequestService) {
		this.leavePolicyService = leavePolicyService;
		this.leaveRequestService = leaveRequestService;
	}
	
	@GetMapping("/all")
	public String viewLeaveApplications( Model model){
		model.addAttribute("leaveRequests", leaveRequestService.getAllLeaveRequests());
		model.addAttribute("pageTitle", "Leave Applications");
		return "pages/admin/leave/leave-list";
	}
	@GetMapping("/myApplications")
	public String viewMyLeaveApplications( Model model){
//		List<LeaveEntitlement> leaveEntitlementList=leaveEntitlementService.getAll();
//		model.addAttribute("leaveEntitlementList",leaveEntitlementList);
		model.addAttribute("pageTitle", "Leave Applications");
		return "pages/admin/leave/my-leave-list";
	}
	
	@GetMapping("/apply")
	public String applyLeave( Model model){
		model.addAttribute("leaveRequest", new LeaveRequestDTO());
		List<LeavePolicy> leavePolicies=leavePolicyService.getAll();
		model.addAttribute("leaveType",leavePolicies);
		model.addAttribute("pageTitle", "Apply");
		return "pages/admin/leave/apply-leave";
	}
	@GetMapping("/viewLeaveBalanceReport")
	public String viewLeaveBalanceReport( Model model){

		model.addAttribute("pageTitle", "Leave Entitlements and Usage Report");
		return "pages/admin/leave/leave-balance-report";
	}
	

	
	
	
	@GetMapping("/{id}")
	public String getLeaveRequestById(@PathVariable Integer id, Model model) {
		model.addAttribute("leaveRequest", leaveRequestService.getLeaveRequestById(id));
		return "leaveRequestDetails"; // name of the Thymeleaf template
	}
	

	
	@PostMapping("/create")
	public String createLeaveRequest(@ModelAttribute LeaveRequestDTO leaveRequestDTO, Model model) {
		LeaveRequestDTO newLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequestDTO);
		model.addAttribute("leaveRequest", newLeaveRequest);
		return "redirect:/system/leave/application/all"; // Redirect after creating leave request
	}
	
}
