/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 *
 * @author UTJ
 */
@Entity
@Table(name = "common")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Common__ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "desc__")
    private String desc__;
    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updatedAt")
    private LocalDateTime lastUpdated;

    
    
}
