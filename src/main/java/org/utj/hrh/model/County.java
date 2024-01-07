package org.utj.hrh.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@Column(name = "CountyID")
	private Integer countyId;
	
	@Column(name = "county_name", nullable = false)
	private String countyName;
	
	@Column(name = "RegionID")
	private String regionId;
	
	@Column(name = "burden_cartegory")
	private String burdenCategory;
	
	@Column(name = "CountyMFL")
	private String countyMFL;
	
	@Column(name = "isactive", columnDefinition = "varchar(45) default '1'")
	private String isActive;
	
	
	@OneToMany(mappedBy = "county", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubCounty> subCounties ;
}
