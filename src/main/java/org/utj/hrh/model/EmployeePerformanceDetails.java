package org.utj.hrh.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_performance_details") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeePerformanceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_performance_details_id")
    private Long employeePerformanceDetailsId;

    @ManyToOne
    @JoinColumn(name = "employee_performance_id", insertable = false, updatable = false)
    private EmployeePerformance employeePerformance;

//    @ManyToOne
//    @JoinColumn(name = "performance_criteria_ID", insertable = false, updatable = false)
//    private PerformanceCriteria performanceCriteria;

    @Column(name = "rating")
    private Integer rating;
}
