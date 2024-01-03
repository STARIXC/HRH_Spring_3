package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "organization_gen_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "tax_id", length = 30)
    private String taxId;

    @Column(name = "registration_number", length = 30)
    private String registrationNumber;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "fax", length = 30)
    private String fax;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "country", length = 30)
    private String country;

    @Column(name = "county", length = 30)
    private String county;

    @Column(name = "city", length = 30)
    private String city;

    @Column(name = "postal_code", length = 30)
    private String postalCode;

    @Column(name = "street1", length = 100)
    private String street1;

    @Column(name = "street2", length = 100)
    private String street2;

    @Column(name = "note", length = 255)
    private String note;

}
