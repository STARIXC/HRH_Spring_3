package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee_dependants") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dependant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String relationship;
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "emp_number", referencedColumnName = "id")
    private Employee employeeDependant;
}
