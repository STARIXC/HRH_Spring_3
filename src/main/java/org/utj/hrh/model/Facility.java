package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "subpartnera") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facility {
	@Id
	@Column(name = "sub_partner_id")
	private Integer sub_partner_id;

	@Column(name = "sub_partner_nom")
	private String sub_partner_nom;

	@Column(name = "district_id")
	private Integer district_id;

	@Column(name = "centre_sante_id")
	private Integer centre_sante_id;


	@OneToMany(mappedBy = "facility",fetch = FetchType.LAZY)
	private List<EmployeeFacility> employeeFacilities;


}
