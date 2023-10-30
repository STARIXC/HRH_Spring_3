/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 *
 * @author UTJ
 */
@Entity
@Table(name = "district")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubCounty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Integer district_id;

    @Column(name = "county_id")
    private Integer county_id;
//    , county_id, district_nom, mdt_region_id, active

    @Column(name = "district_nom")
    private String district_nom;

    @Column(name = "mdt_region_id")
    private Integer mdt_region_id;

    @Column(name = "active")
    private Integer  active;
//
//
//    @OneToMany(mappedBy = "subCounty")
//    private List<Facility> facilities;
//
//    @ManyToOne
//    @ToString.Exclude
//    @JoinColumn(name="county_id")
//    private County county;
}
