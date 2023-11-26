/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utj.hrh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @author UTJ
 */
@Entity
@Table(name = "tbl_financial_year") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FinancialYear {
    @Id
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "year")
    private String year;
    @Column(name = "start_date")
    private String start_date;
    @Column(name = "end_date")
    private String end_date;
    @Column(name = "contract_period")
    private String contract_period;
    @Column(name = "contract_no_months")
    private String contract_no_months;
    @Column(name = "status")
    private Integer status;


}
