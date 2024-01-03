package org.utj.hrh.dto;


import org.utj.hrh.model.County;
import org.utj.hrh.model.SubCounty;

public record FacilityDTO(
		Integer subPartnerId,
		 String subPartnerName,
		SubCounty subCounty,
		Integer centreSanteId
		) {

	
	

}
