/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author UTJ
 */
@Entity
@Table(name = "supervisors") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "mflc", referencedColumnName = "centre_sante_id")
    private Facility facilitySupervisors;

    @OneToOne(optional = true)
    @JoinColumn(name = "login_id", referencedColumnName = "person_number") // Link to the person using the auto-generated ID
    private Person person;

}
