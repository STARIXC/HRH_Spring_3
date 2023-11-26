package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_award")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeAward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_award_id")
    private Long employeeAwardId;

    @Column(name = "award_name")
    private String awardName;

    @Column(name = "gift_item")
    private String giftItem;

    @Column(name = "month")
    private String month;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "emp_no", insertable = false, updatable = false)
    private Employee employee;

//    @ManyToOne
//    @JoinColumn(name = "department_id", insertable = false, updatable = false)
//    private Department department;
}
