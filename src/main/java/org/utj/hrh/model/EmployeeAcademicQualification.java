package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_education_qualification") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeAcademicQualification {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "employee_education_qualification_id")
private Integer employee_education_qualification_id;
    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "emp_no")
    private Employee academicPerformance;
    @Column(name = "institute")
    private String institute;
    @Column(name = "board_university")
    private String board_university;
    @Column(name = "degree")
    private String degree;
    @Column(name = "result")
    private String result;
    @Column(name = "cgpa")
    private String cgpa;
    @Column(name = "passing_year")
    private String passing_year;
    @Column(name = "year")
    private String year;

    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
