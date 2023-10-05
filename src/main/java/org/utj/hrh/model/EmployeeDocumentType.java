package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 *
 * @author CBwahyi
 */
@Entity
@Table(name = "employee_document_type") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description_")
    private String description;
    @Column(name = "isRequired")
    private String isRequired;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;

}
