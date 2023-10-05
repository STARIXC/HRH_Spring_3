package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_user_facility") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "emp_no")
    private String emp_no;

    @Column(name = "centre_sante_id")
    private int centre_sante_id;

    @OneToOne
    @JoinColumn(name="centre_sante_id", referencedColumnName = "CentreSanteId",insertable = false, updatable = false)
    private Facility facility;

}
