package org.utj.hrh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_entitlements")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveEntitlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_number",referencedColumnName = "id")
    private Employee employeeLeaveEntitlement;

    
    @Column(name = "no_of_days")
    private BigDecimal noDays;
    
    @Column(name = "days_used")
    private BigDecimal daysUsed;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "leave_policy_id", referencedColumnName ="id")
    private LeavePolicy leavePolicy;
    
    @Column(name = "from_date")
    private LocalDate fromDate;
    
    @Column(name = "to_date")
    private LocalDate toDate;
    
    @Column(name = "credited_date")
    private LocalDate creditedDate;
    
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
