package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_user_facility" , uniqueConstraints = @UniqueConstraint(columnNames = "emp_no")) // This annotation specifies the database table name
@Setter
@Getter
@AllArgsConstructor
@ToString
public class EmployeeFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private boolean isActive;  // Indicates the current active facility


    @ManyToOne
    @JoinColumn(name = "centre_sante_id", referencedColumnName = "centre_sante_id")
    private Facility facility;


    @OneToOne
    @JoinColumn(name = "emp_no",referencedColumnName = "emp_no")
    private Employee employee;


    public EmployeeFacility() {
        // Set the default active status to true when creating a new record
        this.isActive = true;
    }

}
