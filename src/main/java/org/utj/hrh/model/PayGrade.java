package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pay_grade")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_grade_id")
    private Long payGradeId;

    @Column(name = "pay_grade_name")
    private String payGradeName;

    @Column(name = "gross_salary")
    private Double grossSalary;

    @Column(name = "percentage_of_basic")
    private Double percentageOfBasic;

    @Column(name = "basic_salary")
    private Double basicSalary;

    @Column(name = "overtime_rate")
    private Double overtimeRate;
}