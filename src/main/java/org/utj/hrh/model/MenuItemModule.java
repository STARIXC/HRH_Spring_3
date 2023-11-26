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
public class MenuItemModule  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String iconClass;
    private String name;
}
