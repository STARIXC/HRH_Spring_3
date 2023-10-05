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
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int application_id;
    @Column(name = "employee_id")
    private String employeeId;
    @Column(name = "leave_id")
    private int leaveTypeId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "num_days")
    private int numDays;
    @Column(name = "l_status")
    private int leave_status;
    @Column(name = "supervisor_approval_status")
    private int supervisorApprovalStatus;
    @Column(name = "technical_monitor_approval_status")
    private int technicalOStatus;
    @Column(name = "supervisor_id")
    private int supervisorId;
    @Column(name = "technical_monitor_id")
    private int technicalOId;
    @Column(name = "application_date")
    private Date applicationDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "technical_monitor_id", referencedColumnName = "technical_monitor_id", insertable = false, updatable = false)
    private TechnicalMonitor technicalMonitor;

    @ManyToOne
    @JoinColumn(name = "leave_id", referencedColumnName = "leave_type_id", insertable = false, updatable = false)
    private Leave leaveType;
}
