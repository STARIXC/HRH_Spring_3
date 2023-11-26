package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "weekly_salary")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeeklySalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekly_salaries_id")
    private Long weeklySalariesId;

    @Column(name = "weekly_grade")
    private String weeklyGrade;

    @Column(name = "weekly_rate")
    private Double weeklyRate;
}