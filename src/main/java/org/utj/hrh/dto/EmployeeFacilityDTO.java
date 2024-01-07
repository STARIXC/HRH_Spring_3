package org.utj.hrh.dto;

import lombok.Data;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.Facility;

@Data
public class EmployeeFacilityDTO {
	private Long id;
	private boolean isActive;
	private Integer facilityId;
	private Long activeEmployeeFacility;
}
