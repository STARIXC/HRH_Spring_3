package org.utj.hrh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utj.hrh.model.LeaveEntitlement;

import java.util.List;

@Controller
@RequestMapping("/system/leave/application")
public class LeaveController {
	@GetMapping("/all")
	public String viewLeaveApplications( Model model){
//		List<LeaveEntitlement> leaveEntitlementList=leaveEntitlementService.getAll();
//		model.addAttribute("leaveEntitlementList",leaveEntitlementList);
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

		model.addAttribute("pageTitle", "Apply");
		return "pages/admin/leave/apply-leave";
	}
	@GetMapping("/viewLeaveBalanceReport")
	public String viewLeaveBalanceReport( Model model){

		model.addAttribute("pageTitle", "Leave Entitlements and Usage Report");
		return "pages/admin/leave/leave-balance-report";
	}
}
