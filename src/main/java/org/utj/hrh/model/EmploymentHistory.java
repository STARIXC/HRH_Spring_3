package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author CBwahyi
 */
@Entity
@Table(name = "employee_hist") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmploymentHistory {
    @Id
    @Column(name = "emprecordid", length = 250)
    private String emprecordid;


    @ManyToOne
    @JoinColumn(name = "facility_id",referencedColumnName = "sub_partner_id")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "position_id",referencedColumnName = "id")
    private Designation employeePosition;

    @Column(name = "date_started")
    private LocalDate dateStarted;

    @Column(name = "date_ended")
    private LocalDate dateEnded;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_year_id")
    private FinancialYear financialYear;

    @Column(name = "months_worked")
    private Integer monthsWorked;

    @Column(name = "expected_months")
    private Integer expectedMonths;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    @ToString.Exclude
    private Employee employeeHistory;

}
