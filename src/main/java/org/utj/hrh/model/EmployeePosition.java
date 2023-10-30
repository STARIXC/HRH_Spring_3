package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author UTJ
 */
@Entity
@Table(name = "cadre_positions") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class EmployeePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "standardized_cadre_id")
    private StandardCarder standard_carder;

    @Column(name = "position_title")
    private String position_title;
    @Column(name = "basic_pay")
    private Integer basic_pay;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}
