package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Table(name = "leave_requests") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Integer application_id;

    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "num_days")
    private Integer numDays;

    @Column(name = "application_date")
    private Date applicationDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "emp_no", insertable = false, updatable = false)
    private Employee employee;


    @ManyToOne
    @JoinColumn(name = "leave_id", referencedColumnName = "leave_type_id", insertable = false, updatable = false)
    private LeaveType leaveType;
}
