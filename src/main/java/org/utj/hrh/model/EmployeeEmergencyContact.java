package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emp_emergency_contact") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeEmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "contact_person_name")
    private String contactPersonName;
    @Column(name = "contact_person_phone")
    private String contactPersonPhone;
    @Column(name = "contact_person_relationship")
    private String contactPersonRelationship;

    @OneToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "emp_no")
    private Employee employeeContact;
}
