package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utj.hrh.model.CalendarEvent;
import org.utj.hrh.services.CalenderEventsServices;

import java.util.List;

@Controller
@RequestMapping("/system/calender")
public class CalenderController {

    private final CalenderEventsServices calenderEventsServices;
@Autowired
    public CalenderController(CalenderEventsServices calenderEventsServices) {
        this.calenderEventsServices = calenderEventsServices;
    }

    @GetMapping("/view")
    public String viewCarderTypes(Model model) {

        model.addAttribute("pageTitle", "View :: Calender");
        return "/pages/admin/leave/calender";
    }

    @GetMapping("/getHolidays")
    public List<CalendarEvent> getHolidays() {

        return calenderEventsServices.getHolidays();
    }
}
