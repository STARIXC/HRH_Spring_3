package org.utj.hrh.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class EmploymentHistoryDTO {
	private String emprecordid;
	private Long facilityId; // Replacing the direct Facility reference
	private Long employeePositionId; // Replacing the direct Designation reference
	private LocalDate dateStarted;
	private LocalDate dateEnded;
	private Integer financialYearId;
	private Integer monthsWorked;
	private String currentContract;
	private Integer contractPeriod;
	private LocalDate contractStartDate;
	private LocalDate contractEndDate;
	private Integer expectedMonths;
	private Boolean isActive;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
