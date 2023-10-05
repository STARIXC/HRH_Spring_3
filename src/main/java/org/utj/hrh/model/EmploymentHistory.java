package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "empno", length = 255)
    private String empNo;

    @Column(name = "mfl", length = 11)
    private Integer mfl;

    @Column(name = "position_id")
    private Integer positionId;

    @Column(name = "date_started")
    private String dateStarted; // Storing as string for now, could consider LocalDate in the future

    @Column(name = "date_ended")
    private String dateEnded; // Storing as string for now, could consider LocalDate in the future

    @Column(name = "financial_year", length = 45)
    private String financialYear;

    @Column(name = "months_worked")
    private Integer monthsWorked;

    @Column(name = "current_contract")
    private String currentContract;

    @Column(name = "contract_period")
    private Integer contractPeriod;

    @Column(name = "contract_end_date")
    private String contractEndDate; // Storing as string for now, could consider LocalDate in the future

    @Column(name = "expected_months")
    private Integer expectedMonths;

    @Column(name = "isActive", columnDefinition = "ENUM('1', '0')")
    private String isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;




}
