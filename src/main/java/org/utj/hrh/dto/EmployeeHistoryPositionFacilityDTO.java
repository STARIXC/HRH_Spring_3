package org.utj.hrh.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeHistoryPositionFacilityDTO {
	
	private Integer designationId;
	private Integer facilityId;
	private String emprecordid;
	private LocalDate dateStarted;
	private LocalDate dateEnded;
	private Integer financialYearId;
	private Integer monthsWorked;
	private String currentContract;
	private Integer contractPeriod;
	private LocalDate contractStartDate;
	private LocalDate contractEndDate;
	private Integer expectedMonths;
	private Long employeeId;
	private Long empFacilityId;
	
	
}
