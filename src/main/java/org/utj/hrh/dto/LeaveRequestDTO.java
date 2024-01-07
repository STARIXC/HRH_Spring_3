package org.utj.hrh.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LeaveRequestDTO {
	private Integer applicationId;
	private Date startDate;
	private Date endDate;
	private Integer numDays;
	private Date applicationDate;
	private Long employeeId;
	private Integer leaveTypeId;
}
