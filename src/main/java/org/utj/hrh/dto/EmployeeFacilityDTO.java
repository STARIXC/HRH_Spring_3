package org.utj.hrh.dto;

import lombok.Data;

@Data
public class EmployeeFacilityDTO {
	private Integer subPartnerId;
	private String subPartnerName;
	private Integer districtId;
	private String districtName;
	private Integer countyId;
	private String countyName;
}
