package org.utj.hrh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utj.hrh.model.Holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    List<Holiday> findByStartDateBetweenAndEndDateBetween(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2);

}
