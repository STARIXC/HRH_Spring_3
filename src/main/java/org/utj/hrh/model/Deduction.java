package org.utj.hrh.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "deduction")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Deduction {
    @Id
    private Long deductionId;
    private String deductionName;
    private String deductionType;
    private Double percentageOfBasic;
    private Double limitPerMonth;
}
