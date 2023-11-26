package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Holiday;
import org.utj.hrh.repository.HolidayRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;
    @Autowired
    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<LocalDate> findHolidaysInRange(LocalDate startDate, LocalDate endDate) {
        // Step 2: Set up the query to find holidays that fall within the specified date range.
        List<Holiday> results = holidayRepository.findByStartDateBetweenAndEndDateBetween(startDate, endDate, startDate, endDate);

        // Step 3: Initialize an empty list holiday_extract to store holiday dates.
        List<LocalDate> holidayExtract = results.stream()
                .flatMap(holiday -> createDateRangeList(holiday.getStartDate(), holiday.getEndDate()).stream())
                .collect(Collectors.toList());

        // Step 4: Create an array given_date_extract by extracting dates between start and end dates.
        List<LocalDate> givenDateExtract = createDateRangeList(startDate, endDate);

        // Step 5: Loop through each holiday in the results list.
        // Step 6: Merge these extracted dates with the holiday_extract list.
        // (Steps 5 and 6 are combined in the Java Stream operations below.)
        holidayExtract.addAll(results.stream()
                .flatMap(holiday -> createDateRangeList(holiday.getStartDate(), holiday.getEndDate()).stream())
                .collect(Collectors.toList()));

        // Step 7: Find common dates between given_date_extract and holiday_extract lists.
        List<LocalDate> commonDates = givenDateExtract.stream()
                .filter(holidayExtract::contains)
                .collect(Collectors.toList());

        // Return the list of holiday dates that fall within the specified date range.
        return commonDates;
    }

    // Helper method to create a list of dates between start and end dates.
    private List<LocalDate> createDateRangeList(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dateRange = startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
        return dateRange;
    }

    public List<Holiday> getAll() {
        return holidayRepository.findAll();
    }

    public void save(Holiday holiday) {
         holidayRepository.save(holiday);
    }

    public void delete(Long id) {
        holidayRepository.deleteById(id);
    }


//
//
//    public boolean isLeaveRecordedExistBetweenDate(LocalDate startDate, LocalDate endDate, Long userId, Optional<Integer> fYear) {
//        if (fYear.isEmpty()) {
//            FinancialYear currentFinancialYear = findFinancialYearFromDate(startDate);
//            fYear = Optional.of(currentFinancialYear.getId());
//        }
//
//        long count = leaveRepository.countByUserIdAndFYearAndLeaveDateBetween(userId, fYear.get(), startDate, endDate);
//        return count > 0;
//    }
//
//    private FinancialYear findFinancialYearFromDate(LocalDate startDate) {
//    }

}
