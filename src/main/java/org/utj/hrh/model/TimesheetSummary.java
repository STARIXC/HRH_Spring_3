package org.utj.hrh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author CBwahyi
 */
@Entity
@Table(name = "timesheet_logs") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimesheetSummary {
    @Id
    @Column(name = "log_id")
    private String log_id;
    @Column(name = "log_no")
    private String log_no;
    @Column(name = "emp_no")
    private String emp_no;
    @Column(name = "log_date")
    private String log_date;
    @Column(name = "month")
    private String month;
    @Column(name = "year")
    private String year;
    @Column(name = "hours_worked")
    private int hoursWorked;
    @Column(name = "leave_hours")
    private int leaveHours;
    @Column(name = "holiday_hours")
    private int holidayHours;
    @Column(name = "extra_hours")
    private int extraHours;
    @Column(name = "total_hours")
    private int totalHours;
    @Column(name = "expected_hours")
    private int expectedHours;
    @Column(name = "tested")
    private String tested;
    @Column(name = "pos")
    private String pos;
    @Column(name = "status")
    private int status;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;


}
