package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_employee_position_relations")
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

    @OneToOne
    @JoinColumn(name = "emp_number", referencedColumnName = "emp_no")
    private Employee employee;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "position_id")
    private Designation employeePosition;



}
