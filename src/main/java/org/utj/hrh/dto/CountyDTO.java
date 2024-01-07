package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CountyDTO {
	private Integer countyId;
	private String countyName;
	private String regionId;
	private String burdenCategory;
	private String countyMFL;
	private String isActive;
}
