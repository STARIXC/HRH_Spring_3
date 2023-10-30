/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author UTJ
 */
@Entity
@Table(name = "cadre_category")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarderCat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cadre_category_name")
    private String cadre_category_name;

    @Column(name = "createdAt")
    private LocalDateTime dateCreated;
    @Column(name = "updatedAt")
    private LocalDateTime lastUpdated;


    @OneToMany(mappedBy = "carderCat")
    private List< StandardCarder > standardCarderList ;


}
