package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author CBwahyi
 */

@Entity
@Table(name = "technical_monitors") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TechnicalMonitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technical_monitor_id")
    private Integer technicalMonitorId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mdtRegion")
    private Integer mdtRegion;
    @Column(name = "active")
    private Integer active;
    @OneToOne(optional = true)
    @JoinColumn(name = "login_id", referencedColumnName = "person_number") // Link to the person using the auto-generated ID
    private Person person; // Reference to the person


    

}
