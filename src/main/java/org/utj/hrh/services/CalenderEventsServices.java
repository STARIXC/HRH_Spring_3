package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.CalendarEvent;
import org.utj.hrh.model.Holiday;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalenderEventsServices {

    private final HolidayService holidayService;

    @Autowired
    public CalenderEventsServices(HolidayService holidayService) {
        this.holidayService = holidayService;
    }


    public  List<CalendarEvent> getHolidays() {
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
        List<CalendarEvent> events = new ArrayList<>();
        for (Holiday holiday : currentYearHolidays) {
            // Assuming you have a method to create a CalendarEvent from a Holiday
            CalendarEvent calendarEvent = new CalendarEvent(holiday.getHolidayName(), holiday.getStartDate().atStartOfDay(), holiday.getEndDate() != null ? holiday.getEndDate().atStartOfDay() : null, true, "bg-success");

            events.add(calendarEvent);
        }

//        // Add sample events
//        events.add();
//        events.add(new CalendarEvent("Factory visit", LocalDateTime.now().plusSeconds(218), null, true, "bg-primary"));
//        events.add(new CalendarEvent("Meeting with developer", LocalDateTime.now().plusSeconds(418), null, false, "bg-danger"));
//        events.add(new CalendarEvent("Design proposal", LocalDateTime.now().plusSeconds(718), null, true, "bg-info"));
//        events.add(new CalendarEvent("Web design", LocalDateTime.now().plusSeconds(818), null, false, "bg-warning"));
//        events.add(new CalendarEvent("Cash out", LocalDateTime.now().plusSeconds(1018), null, true, "bg-secondary"));
//        events.add(new CalendarEvent("Factory Visit", LocalDateTime.now().plusSeconds(1218), null, false, "bg-success"));
//        events.add(new CalendarEvent("Factory Visit", LocalDateTime.now().plusSeconds(418), null, true, "bg-primary"));
        System.out.println(events);
        return events;
    }

}
