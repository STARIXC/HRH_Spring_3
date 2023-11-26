package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "termination_types")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TerminationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "termination_name")
    private String terminationName;

    @Column(name = "termination_description")
    private String terminationDescription;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
