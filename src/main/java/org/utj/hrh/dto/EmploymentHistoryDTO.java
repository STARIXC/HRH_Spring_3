package org.utj.hrh.dto;


import lombok.Data;
import java.time.LocalDateTime;
@Data
public class EmploymentHistoryDTO {
	private String emprecordid;
	private Long facilityId; // Replacing the direct Facility reference
	private Long employeePositionId; // Replacing the direct Designation reference
	private String dateStarted;
	private String dateEnded;
	private String financialYear;
	private Integer monthsWorked;
	private String currentContract;
	private Integer contractPeriod;
	private String contractEndDate;
	private Integer expectedMonths;
	private String isActive;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
