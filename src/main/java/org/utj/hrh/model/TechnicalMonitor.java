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
    private int technicalMonitorId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mdtRegion")
    private int mdtRegion;
    @Column(name = "active")
    private int active;
//    @Column(name = "login_id")
//    private String loginId;

    @OneToOne
    @JoinColumn(name = "login_id", referencedColumnName = "userid",unique = true, insertable = false, updatable = false)
    private User user;

}
