package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_address_settings")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyAddressSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_address_setting_id")
    private Long companyAddressSettingId;
    @Column(name = "address")
    private String address;
}
