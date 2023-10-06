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
    private Integer id;
    @Column(length = 3, nullable = false)
    private Integer carder_category_id;
    @Column(length = 3, nullable = false)
    private Integer standardized_cadre_id;
    @Column(nullable = false)
    private String position_title;
    @Column(length = 6)
    private Integer basic_pay;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;

}