package org.utj.hrh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeContactController {
	
	
	@GetMapping("/employee/contactDetails")
	public String getContactDetails(@RequestParam String empNo, Model model) {
		// Logic to get contact details
//		model.addAttribute("contactDetails", contactDetailsService.getContactDetails(empNo));
		return "contact-details-fragment";
	}
	
}
