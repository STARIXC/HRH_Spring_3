package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_employee_position_relations") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PositionEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Column(name = "emp_no")
//    private String emp_no;
//
//    @Column(name = "position_id")
//    private int position_id;

    @OneToOne
//    @ToString.Exclude // Exclude this field from the generated toString() method
    @JoinColumn(name = "emp_no")
    private Employee employee;

    @ManyToOne
    @ToString.Exclude // Exclude this field from the generated toString() method
    @JoinColumn(name = "position_id")
    private EmployeePosition employeePosition;



}
