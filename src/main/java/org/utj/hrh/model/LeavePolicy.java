package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeavePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Double accrued_amount;
    private String accrued_based_on;
    private Integer accrued_max_days;
    private Integer applicable_from_days;
    @Column(name = "apply_for_new_users", columnDefinition = "BIT(1)")
    private Boolean apply_for_new_users;
    @Column(name = "apply_for_exist_users", columnDefinition = "BIT(1)")
    private Boolean apply_for_exist_users;
    private Integer carryover_days;
    private Integer carryover_uses_limit;
    private String color;
    private Integer days;
    private String description;
    @ManyToOne
    @JoinColumn(name = "employee_type", referencedColumnName = "id")
    private CarderType employee_type;

    private String forward_default;
    private String gender;
    @Column(name = "half_day_enable", columnDefinition = "BIT(1)")
    private Boolean halfDayEnable;
    private String marital;
    @ManyToOne
    @JoinColumn(name = "f_year", referencedColumnName = "id")
    private FinancialYear f_year;
    @ManyToOne
    @JoinColumn(name = "leave_id", referencedColumnName = "leave_type_id")
    private LeaveType leaveType;


    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
