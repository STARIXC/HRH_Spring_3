package org.utj.hrh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utj.hrh.model.Facility;

import java.util.List;

@Controller
@RequestMapping("/system/leave/entitlement")
public class LeaveEntitlement {
    @GetMapping("/all")
    public String viewLeaveEntitlement( Model model){
//        List<Facility> facilities=facilityRepository.findAll();
//        model.addAttribute("facilities",facilities);
        model.addAttribute("pageTitle", "Leave Entitlement");
        return "pages/admin/leave/leave-entitlement";
    }
}
