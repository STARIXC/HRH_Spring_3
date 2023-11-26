package org.utj.hrh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utj.hrh.model.FinancialYear;
import org.utj.hrh.services.FinancialYearService;

import java.util.List;

@Controller
@RequestMapping("/system/financial-year")
public class FinancialYearController {

    private final FinancialYearService financialYearService;

    public FinancialYearController(FinancialYearService financialYearService) {
        this.financialYearService = financialYearService;
    }

    @GetMapping("/list")
    public String viewFYs(Model model) {
        FinancialYear financialYear = new FinancialYear();
        List<FinancialYear> financialYears = financialYearService.getAllFY();
        model.addAttribute("financialYear", financialYear);
        model.addAttribute("financialYears", financialYears);
        model.addAttribute("pageTitle", "Financial Years");
        return "pages/admin/Administration/financial";
    }
    @PostMapping("/save")
    public String saveFY(@ModelAttribute("financialYear") FinancialYear financialYear) {
        if (financialYear.getId() == null) {
            // It's a new role, so save it as a new record
            financialYearService.save(financialYear);
        } else {
            // It's an edit, so update the existing role
            financialYearService.save(financialYear);
        }
        return "redirect:/system/financial/list"; // Redirect back to the same page
    }

}
