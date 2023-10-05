package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "county")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countyID")
   private int CountyID;
	@Column(name = "county")
    private String County;

	@Column(name = "regionID")
	private int regionID;

	@Column(name = "burden_cartegory")
	private String  burden_cartegory;

	@Column(name = "active")
	private int active;


}
