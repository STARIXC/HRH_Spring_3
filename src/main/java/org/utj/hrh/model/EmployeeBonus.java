package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bonus_setting")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeBonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_bonus_id")
    private Long employeeBonusId;

//    @Column(name = "bonus_setting_id")
//    private Long bonusSettingId;
//
//    @Column(name = "employee_id")
//    private Long employeeId;

    @Column(name = "month")
    private String month;

    @Column(name = "gross_salary")
    private Double grossSalary;

    @Column(name = "basic_salary")
    private Double basicSalary;

    @Column(name = "bonus_amount")
    private Double bonusAmount;

    @Column(name = "tax")
    private Double tax;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "bonus_setting_id", insertable = false, updatable = false)
    private BonusSetting bonusSetting;

    @ManyToOne
    @JoinColumn(name = "pay_grade_ID", insertable = false, updatable = false)
    private PayGrade payGrade;

    @ManyToOne
    @JoinColumn(name = "weekly_salary_ID")
    private WeeklySalary weeklySalary;
}
