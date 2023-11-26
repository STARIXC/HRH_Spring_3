package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pay_grade_to_allowance")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayGradeToAllowance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_grade_to_allowance_id")
    private Long payGradeToAllowanceId;

    @Column(name = "pay_grade_id")
    private Long payGradeId;

    @Column(name = "allowance_id")
    private Long allowanceId;
}
