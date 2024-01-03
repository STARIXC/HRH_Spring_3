package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "employee_performance") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeePerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_performance_id")
    private Long employeePerformanceId;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id",insertable = false, updatable = false)
    private Employee employeePerformance;

    @Column(name = "month")
    private String month;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "employeePerformance",fetch = FetchType.LAZY)
    private List<EmployeePerformanceDetails> employeePerformances;

}
