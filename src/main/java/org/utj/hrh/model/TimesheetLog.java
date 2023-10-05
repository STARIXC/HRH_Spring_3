package org.utj.hrh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "timesheet_log_activities") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimesheetLog {
    @Id
    @Column(name = "activity_log_id")
    private String activity_log_id;
    @Column(name = "activity_code")
    private int activity_code;
    @Column(name = "hours_worked")
    private String  hours_worked;
    @Column(name = "leave_hours")
    private String leave_hours;
    @Column(name = "public_holiday")
    private String holiday_hours;
    @Column(name = "extra")
    private String extra_hours_worked;
    @Column(name = "log_date")
    private Date log_date;

    @Column(name = "emp_no")
    private String emp_no;
    @Column(name = "log_no")
    private String log_no;
    @Column(name = "activity_desc")
    private String activity_desc;
    @Column(name = "month")
    private String month;
    @Column(name = "year")
    private String year;


}
