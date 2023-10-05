package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_permission")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "role_id")
    private int role_id;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
