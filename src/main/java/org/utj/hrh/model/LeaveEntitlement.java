package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leave_entitlements")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveEntitlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_id", referencedColumnName ="leave_type_id")
    private LeaveType leave;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "entitlement")
    private Integer entitlement;

    @Column(name = "used")
    private Integer used;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
