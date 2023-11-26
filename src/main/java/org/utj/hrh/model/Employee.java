package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    public static final String GENDER_MALE = "MALE";
    public static final String GENDER_FEMALE = "FEMALE";
    public static final String GENDER_OTHER = "OTHER";

    public static final String MARITAL_STATUS_SINGLE = "Single";
    public static final String MARITAL_STATUS_MARRIED = "Married";
    public static final String MARITAL_STATUS_OTHER = "Other";

    @Id
    @Column(name = "id")
    private String id;

    @OneToOne(optional = true)
    @JoinColumn(name = "emp_no", referencedColumnName = "person_number") // Link to the person using the auto-generated ID
    private Person person; // Reference to the person

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

    @ManyToOne
    @JoinColumn(name = "status")
    private EmployeeStatus status;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private EmployeePosition position;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "employee")
    private Set<Document> documents;

    @OneToOne(mappedBy = "employeeContact", optional = true)
    private EmployeeEmergencyContact employeeEmergencyContact;
//
    @OneToMany(mappedBy = "expEmployee")
    private Set<EmployeeExperience> employeeExperiences;

    @OneToMany(mappedBy = "academicPerformance")
    private Set<EmployeeAcademicQualification> academicQualifications;
//
    private boolean hasLoginAccess;
//
@OneToOne(mappedBy = "employee", optional = true)
private EmployeeFacility employeeFacility;
//
    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<EmploymentHistory> employmentHistory;
//
    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<EmployeeAward> employeeAwards;

    @OneToMany(mappedBy = "employeePerformance",fetch = FetchType.LAZY)
    private List<EmployeePerformance> employeePerformances;

    @OneToMany(mappedBy = "employeeLicense",fetch = FetchType.LAZY)
    private List<EmployeeLicense> employeeLicenses;


    public String getGenderString() {
        if (gender == null) {
            return null;
        }
        switch (gender) {
            case GENDER_MALE:
                return "Male";
            case GENDER_FEMALE:
                return "Female";
            default:
                return null; // You may want to throw an exception or handle this differently
        }
    }
}
