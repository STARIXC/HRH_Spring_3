package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "emp_number", referencedColumnName = "id")
    private Employee employeeContact;
    
    @Column(name = "eec_name", length = 100, nullable = true, columnDefinition = "varchar(100) default ''")
    private String name;
    
    @Column(name = "relationship", length = 100, nullable = true, columnDefinition = "varchar(100) default ''")
    private String relationship;
    
    @Column(name = "home_no", length = 100, nullable = true, columnDefinition = "varchar(100) default ''")
    private String homePhone;
    
    @Column(name = "mobile_no", length = 100, nullable = true, columnDefinition = "varchar(100) default ''")
    private String mobilePhone;
    
    @Column(name = "office_no", length = 100, nullable = true, columnDefinition = "varchar(100) default ''")
    private String officePhone;
}
