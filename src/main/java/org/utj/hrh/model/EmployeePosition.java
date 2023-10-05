package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author UTJ
 */
@Entity
@Table(name = "cadre_positions") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "carder_category_id")
    private int carder_category_id;
    @Column(name = "standardized_cadre_id")
    private int standardized_cadre_id;
    @Column(name = "position_title")
    private String position_title;
    @Column(name = "basic_pay")
    private int basic_pay;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
