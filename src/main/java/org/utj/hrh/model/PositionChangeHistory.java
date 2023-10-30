package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PositionChangeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private EmployeePosition oldPosition;

    @ManyToOne
    private EmployeePosition newPosition;

    @Column(name = "change_date")
    @UpdateTimestamp
    private LocalDateTime changeDate;

}
