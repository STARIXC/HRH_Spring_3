
package org.utj.hrh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

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
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "year")
    private String year;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "contract_period")
    private Integer contractPeriod;
    @Column(name = "contract_no_months")
    private String contractNoMonths;
    @Column(name = "status")
    private Integer status;


}
