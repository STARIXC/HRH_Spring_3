package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 *
 * @author STARIXC
 */
@Entity
@Table(name = "cadre_type")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "cadre_type_name")
    private String cadre_type_name;
    @Column(name = "hrs_per_week")
    private String hrs_per_week;


    @Column(name = "createdAt")
    private LocalDateTime dateCreated;
    @Column(name = "updatedAt")
    private LocalDateTime lastUpdated;


}
