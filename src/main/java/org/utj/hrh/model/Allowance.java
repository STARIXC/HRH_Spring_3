package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "allowance")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Allowance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allowanceId;
    private String allowanceName;
    private String allowanceType;
    private Double percentageOfBasic;
    private Double limitPerMonth;
}
