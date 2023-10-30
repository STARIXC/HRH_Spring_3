package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author CBwahyi
 */
@Entity
@Table(name = "documents") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "docIdentifier")
    private String docID;

    @Column(name = "document_value")
    private String documentValue;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    @ToString.Exclude
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "emp_no")
    @ToString.Exclude
    private Employee employee;
}
