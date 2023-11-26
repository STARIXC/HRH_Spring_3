
package org.utj.hrh.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UTJ
 */
@Entity
@Table(name = StandardCarder.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties("standardCarderList")
public class StandardCarder {
    public static final String TABLE_NAME = "standardized_cadre";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 40, nullable = false)
    private String standardized_cadre_name;

    @OneToMany(mappedBy = "standard_carder")
    private List<EmployeePosition> designations ;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "carder_type_id")
    private CarderType carderType;

    @ManyToOne
    @JoinColumn(name = "carder_category_id")
    @ToString.Exclude
    private CarderCat carderCat;
}
