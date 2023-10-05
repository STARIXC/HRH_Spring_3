package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author UTJ
 */

@Entity
@Table(name = "emp_bio") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "emp_no")
    private String empNo;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "other_name")
    private String otherName;
    @Column(name = "national_id")
    private String nationalId;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "alternative_phone")
    private String altPhone;
    @Column(name = "alternative_email")
    private String altEmail;
    @Column(name = "dob")
    private String dob;
    @Column(name = "home_address")
    private String homeAddress;
    @Column(name = "present_address")
    private String presentAddress;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "marital_status")
    private String maritalStatus;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "disability")
    private String disability;
    @Column(name = "disability_explain")
    private String disabilityExplain;
    @Column(name = "kra_pin")
    private String kraPin;
    @Column(name = "nssf_no")
    private String nssfNo;
    @Column(name = "nhif_no")
    private String nhifNo;
    @Column(name = "cert_good_conduct_no")
    private String certGoodConductNo;
    @Column(name = "helb_clearance_no")
    private String helbClearanceNo;
    @Column(name = "helbBenefitiary")
    private int helbBenefitiary;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "branch")
    private String branch;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "date_started")
    private String dateStarted;
    @Column(name = "date_ended")
    private String dateEnded;
     @Column(name = "status")
    private int status;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "userid", insertable = false, updatable = false)
    private User user;

    @OneToOne
    @JoinColumn(name="id", referencedColumnName = "emp_no",insertable = false, updatable = false)
    private EmployeeFacility employeeFacility;




}