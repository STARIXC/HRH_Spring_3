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
    private Integer id;
    private String action;
    private String menuUrl;
    private String name;
    private Integer parentId;
    private int status;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private MenuItemModule  module;



}
