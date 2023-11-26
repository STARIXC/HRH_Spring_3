package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.utj.hrh.model.Holiday;
import org.utj.hrh.services.EntityNotFoundException;
import org.utj.hrh.services.HolidayService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/system/leave/holidays")
public class LeaveHolidayController {
    private final HolidayService holidayService;

    @Autowired
    public LeaveHolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

//    @GetMapping("/manage")
//    public String viewLeaveHolidays(Model model) {
//        Holiday holiday = new Holiday();
//        model.addAttribute("holiday", holiday);
//        List<Holiday> leaveHolidays = holidayService.getAll();
//        model.addAttribute("leaveHolidays", leaveHolidays);
//               model.addAttribute("pageTitle", "View :: Holiday");
//        return "pages/admin/holidays/holidays";
//    }

    @PostMapping("/save")
    public String saveHoliday(Holiday holiday, RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        if (holiday.getRangeStatus() == true) {
            holidayService.save(holiday);

        } else {
            // Range is not enabled, set endDate to startDate
            holiday.setEndDate(holiday.getStartDate());
            holidayService.save(holiday);
        }
        redirectAttributes.addFlashAttribute("message", "Record added successfully");
        return "redirect:/system/leave/holidays/manage";
    }

    @GetMapping("/manage")
    public String getCurrentYearHolidays(Model model) {
        Holiday lHoliday = new Holiday();
        model.addAttribute("holiday", lHoliday);
        // Get all holidays
        List<Holiday> allHolidays = holidayService.getAll();

        // Calculate the date range for the current year or previous year
        LocalDate startDateFilter;
        LocalDate endDateFilter;

        if (LocalDate.now().getMonthValue() >= 10) {
            // If the current month is October or later, consider the current year
            startDateFilter = LocalDate.of(LocalDate.now().getYear(), 10, 1);
            endDateFilter = LocalDate.of(LocalDate.now().getYear() + 1, 9, 30);
        } else {
            // If the current month is January to September, consider the previous year
            startDateFilter = LocalDate.of(LocalDate.now().getYear() - 1, 10, 1);
            endDateFilter = LocalDate.of(LocalDate.now().getYear(), 9, 30);
        }

        // Filter holidays for the specified date range
        List<Holiday> currentYearHolidays = allHolidays.stream()
                .filter(holiday -> holiday.getStartDate().isAfter(startDateFilter) && holiday.getStartDate().isBefore(endDateFilter))
                .collect(Collectors.toList());

        model.addAttribute("leaveHolidays", currentYearHolidays);

        // Add other necessary model attributes

        return "pages/admin/holidays/holidays"; // The name of your Thymeleaf template
    }
    @GetMapping("/delete/{id}")
    public String deleteDesignation(@PathVariable(name = "id") Long id, Model model, RedirectAttributes redirectAttributes){
                    holidayService.delete(id);
            redirectAttributes.addFlashAttribute("message","Record deleted successfully");

        return "redirect:/system/leave/holidays/manage";
    }
}
