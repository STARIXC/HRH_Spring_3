package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_status") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
}
