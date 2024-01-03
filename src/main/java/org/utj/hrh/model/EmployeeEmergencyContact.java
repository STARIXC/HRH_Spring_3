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
    @Column(name = "seq_number", precision = 2, scale = 0, columnDefinition = "decimal default 0")
    private BigDecimal seqNo = BigDecimal.ZERO;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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
