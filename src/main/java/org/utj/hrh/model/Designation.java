package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = Designation.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Designation {

    public static final String TABLE_NAME = "cadre_positions";
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