package org.utj.hrh.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "earn_leave_rule")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EarnLeaveRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long earnLeaveRuleId;
    private String forMonth;
    private Integer dayOfEarnLeave;
}
