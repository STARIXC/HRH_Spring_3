package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notice")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "attach_file")
    private String attachFile;

    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;


    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "userid", insertable = false, updatable = false)
    private User createdByEmployee;



}
