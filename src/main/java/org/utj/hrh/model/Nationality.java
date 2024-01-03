package org.utj.hrh.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nationality")
public class Nationality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name", length = 100)
    private String name;

    // Constructors, getters, and setters
}
