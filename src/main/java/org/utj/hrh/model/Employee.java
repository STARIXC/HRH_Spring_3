package org.utj.hrh.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = Employee.TABLE_NAME) // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    public static final String TABLE_NAME = "employee";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "person_number")
    @JsonManagedReference
    private Person person;

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

    @OneToOne
    private Address presentAddress;
    @OneToOne
    private Address homeAddress;

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
    @Column(name = "helb_beneficiary")
    private Integer helbBeneficiary;
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

    @ManyToOne
    @JoinColumn(name = "status")
    private EmployeeStatus status;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Designation position;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;

    private boolean hasLoginAccess;
    
    @OneToOne(mappedBy = "employee")
    private EmployeeFacility employeeFacility;
    
    @OneToMany(mappedBy = "academicQualification")
    @ToString.Exclude
    private List<EmployeeEducation> educationList;
    
    @OneToMany(mappedBy = "documentOwner")
    @ToString.Exclude
    private List<Document> documents;

    @OneToMany(mappedBy = "employeeHistory")
    @ToString.Exclude
    private List<EmploymentHistory> employmentHistory;

    @OneToMany(mappedBy = "employeeAward")
    @ToString.Exclude
    private List<EmployeeAward> employeeAwards;

    @OneToMany(mappedBy = "employeePerformance")
    @ToString.Exclude
    private List<EmployeePerformance> employeePerformances;

    @OneToMany(mappedBy = "employeeLicense")
    @ToString.Exclude
    private List<EmployeeLicense> employeeLicenses;
//
    @OneToMany(mappedBy = "employeeContact")
    @ToString.Exclude
    private List<EmployeeEmergencyContact> employeeContacts;
    
    @OneToMany(mappedBy = "employeeLeaveEntitlement")
    @ToString.Exclude
    private List<LeaveEntitlement> leaveEntitlements;
    
}
