package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leave_approval") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "leave_request_id")
    private LeaveRequest leaveRequest;

    @Column(name = "supervisor_approval_status")
    private Integer supervisorApprovalStatus;

    @Column(name = "technical_monitor_approval_status")
    private Integer technicalOStatus;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "technical_monitor_id", referencedColumnName = "technical_monitor_id", insertable = false, updatable = false)
    private TechnicalMonitor technicalMonitor;

    private boolean approved;
}
