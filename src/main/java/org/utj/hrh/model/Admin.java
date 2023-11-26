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


    @Column(name = "full_name")
    private String fullName;

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "person_number") // Link to the person using the auto-generated ID
    private Person person; // Reference to the person
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role; // Relationship with the Role table


}

