package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_experience") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeExperience {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_experience_id")
    private Integer employee_experience_id;


    @Column(name = "organization_name")
    private String organization_name;
    @Column(name = "designation")
    private String designation;
    @Column(name = "from_date")
    private String from_date;
    @Column(name = "to_date")
    private String to_date;
    @Column(name = "skill")
    private String skill;
    @Column(name = "responsibility")
    private String responsibility;

    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;


}
