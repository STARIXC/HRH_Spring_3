package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menus")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "parent_id")
    private int parent_id;
    @Column(name = "action")
    private Integer action; // Use Integer wrapper class

    @Column(name = "name")
    private String name;
    @Column(name = "menu_url")
    private String menu_url;

    @Column(name = "module_id")
    private int module_id;
    @Column(name = "status")
    private int status;
}
