package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leaves") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id")
    private Integer leaveTypeId;
    @Column(name = "accrual_rate")
    private Integer accrualRate;
    @Column(name = "max_days")
    private Integer maxDays;
    @Column(name = "leave_type")
    private String leaveType;
    @Column(name = "description")
    private String description;
    @Column(name = "is_accrued")
    private boolean isAccrued;


}
