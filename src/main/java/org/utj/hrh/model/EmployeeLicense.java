package org.utj.hrh.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee_license")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeLicense  {
    @Id
    @ManyToOne
    @JoinColumn(name = "license_id", referencedColumnName = "id")
    private License license;

    @Id
    @Column(name = "emp_number")
    private Long empNumber;

    @Column(name = "license_no", length = 50, nullable = true)
    private String licenseNo;

    @Column(name = "license_issued_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date licenseIssuedDate;

    @Column(name = "license_expiry_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date licenseExpiryDate;

    @ManyToOne
    @JoinColumn(name = "emp_number", referencedColumnName = "emp_no")
    private Employee employeeLicense;


}
