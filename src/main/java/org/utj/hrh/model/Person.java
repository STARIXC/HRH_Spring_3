package org.utj.hrh.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "person") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"employee"})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "person_number", unique = true)
    private String personNumber;

    @Column(name = "full_name")
    private String name;

    @Column(name = "email_address", nullable = true)
    private String email;

    @Column(name = "phone_number", nullable = true)
    private String phone;


    @OneToOne(mappedBy = "person", optional = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private Employee employee;

}
