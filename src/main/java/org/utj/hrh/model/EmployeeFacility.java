package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_facility")
@Data
public class EmployeeFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "facility_id", referencedColumnName = "sub_partner_id")
    private Facility facility;
    @OneToOne
    @JoinColumn(name = "emp_number", referencedColumnName = "id") // Ensure 'id' matches the column name in Employee table
    private Employee activeEmployeeFacility;



}
