package org.utj.hrh.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userid", unique = true)
    private User user;

    private String full_name;


    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role; // Relationship with the Role table


}

