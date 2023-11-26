package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "education")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Education {

    public static final Integer DELETED = 1;
    public static final Integer ACTIVE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "education")
    private List<EmployeeEducation> employeeEducation;

}
