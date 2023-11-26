package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bonus_setting")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BonusSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bonus_setting_id")
    private Long bonusSettingId;
    @Column(name = "festival_name")
    private String festivalName;
    @Column(name = "percentage_of_bonus")
    private Double percentageOfBonus;
    @Column(name = "bonus_type")
    private String bonusType;
}
