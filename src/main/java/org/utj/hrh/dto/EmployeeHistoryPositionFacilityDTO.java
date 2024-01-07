package org.utj.hrh.dto;

import lombok.Data;

@Data
public class EmployeeHistoryPositionFacilityDTO {
	
	private DesignationDTO designationDTO;
	private FacilityDTO facilityDTO;
	private EmploymentHistoryDTO employmentHistoryDTO;
	private Long employeeId;
	
	
}
