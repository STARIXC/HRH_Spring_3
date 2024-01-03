package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "emp_adress") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long address_id;
    private String street;
    private String postalAddress;
    private String cityTown;
    private String postalCode;
    private String country;
    @ManyToOne
    @JoinColumn(name = "emp_number", referencedColumnName = "id")
    private Employee employeeAddress;
}
