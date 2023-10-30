package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @Column(name = "county_id")
   private Integer county_id;

	@Column(name = "county_name")
    private String county_name;

	@Column(name = "regionID")
	private Integer regionID;

	@Column(name = "burden_cartegory")
	private String  burden_cartegory;

	@Column(name = "active")
	private Integer active;
//
//
//	@OneToMany(mappedBy = "county")
//	private List<SubCounty> subCounties;

}
