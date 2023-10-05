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
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private int status;
    @Column(name = "login_id")
    private String login_id;

    @OneToOne
    @JoinColumn(name = "login_id", referencedColumnName = "userid", insertable = false, updatable = false)
    private User user;
}
