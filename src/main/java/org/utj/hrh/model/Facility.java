package org.utj.hrh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "subpartnera") // This annotation specifies the database table name
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facility {
	@Id
	@Column(name = "SubPartnerID")
	private int SubPartnerID;
	@Column(name = "SubPartnerNom")
	private String SubPartnerNom;
	@Column(name = "DistrictID")
	private int DistrictID;
	@Column(name = "CentreSanteId")
	private int CentreSanteId;

	
	

}
