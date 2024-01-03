package org.utj.hrh.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "emp_education")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emp_number", referencedColumnName = "id")
    private Employee academicQualification;

    @ManyToOne
    @JoinColumn(name = "education_id", referencedColumnName = "id")
    private EducationLevel education;

    @Column(name = "institute", length = 100)
    private String institute;

    @Column(name = "major", length = 100)
    private String major;

    @Column(name = "year")
    private Integer year;

    @Column(name = "score", length = 25)
    private String score;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
    

}

