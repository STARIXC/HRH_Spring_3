package org.utj.hrh.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "license")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "license", cascade = CascadeType.ALL)
    private List<EmployeeLicense> employeeLicenses;

}
