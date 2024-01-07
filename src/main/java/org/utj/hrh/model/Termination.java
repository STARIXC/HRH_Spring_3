/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "termination_id")
    private Integer terminationId;


    @Column(name = "termination_type")
    private String terminationType;

    @Column(name = "subject")
    private String subject;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "notice_date")
    private Date noticeDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "termination_date")
    private Date terminationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    private Employee terminateToEmployee;

    @ManyToOne
    @JoinColumn(name = "terminate_by", referencedColumnName = "userid")
    private User terminateByEmployee;
    
}
