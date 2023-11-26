package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "post")
    private String post;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "application_end_date")
    private String applicationEndDate;

    @Column(name = "created_by")
    private Long createdBy;
//
//    @ManyToOne
//    @JoinColumn(name = "created_by", insertable = false, updatable = false)
//    private User createdByUSer;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "status")
    private Integer status;
}
