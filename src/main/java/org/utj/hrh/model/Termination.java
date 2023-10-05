/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 *
 * @author CBWAHYI
 */

@Entity
@Table(name = "termination")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Termination {
//    String t_id,emp_no,termination_date,notice_date,termination_reason,voluntary_termination;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "termination_id")
    private Long terminationId;

    @Column(name = "terminate_to")
    private String terminateTo;

    @Column(name = "terminate_by")
    private String terminateBy;

    @Column(name = "termination_type")
    private String terminationType;

    @Column(name = "subject")
    private String subject;

    @Column(name = "notice_date")
    private java.util.Date noticeDate;

    @Column(name = "termination_date")
    private Date terminationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "terminate_to", referencedColumnName = "emp_no", insertable = false, updatable = false)
    private Employee terminateToEmployee;

    @ManyToOne
    @JoinColumn(name = "terminate_by", referencedColumnName = "userid", insertable = false, updatable = false)
    private User terminateByEmployee;
    
}
