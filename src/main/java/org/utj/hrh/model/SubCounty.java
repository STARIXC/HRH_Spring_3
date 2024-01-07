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
    @Column(name = "DistrictID")
    private Integer districtId;
    
    @ManyToOne
    @JoinColumn(name = "CountyID")
    private County county;
    
    @Column(name = "district_nom") // Specify the desired column name here
    private String districtName;
    
    @Column(name = "active", columnDefinition = "int default 1")
    private Integer active;
}

