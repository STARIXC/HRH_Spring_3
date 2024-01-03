package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_country")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {

    @Id
    @Column(name = "cou_code", length = 2)
    private String countryCode;

    @Column(name = "name", length = 80)
    private String name;

    @Column(name = "cou_name", length = 80)
    private String countryName;

    @Column(name = "iso3", length = 3)
    private String iso3;

    @Column(name = "numcode")
    private Integer numCode;

}
