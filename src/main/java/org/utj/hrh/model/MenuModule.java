package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "modules")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "icon_class")
    private String iconClass;



}
