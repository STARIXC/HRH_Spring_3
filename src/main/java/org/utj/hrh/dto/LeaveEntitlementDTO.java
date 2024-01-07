package org.utj.hrh.dto;


import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class LeaveEntitlementDTO {
	
	private Long id;
	private Long employeeLeaveEntitlement;
	private BigDecimal noDays;
	private BigDecimal daysUsed;
	private Long leavePolicy;
	private LocalDate fromDate;
	private LocalDate toDate;
	private LocalDate creditedDate;
	private Long createdBy;
	private String note;
	private String employeeFullName;
	private String leaveTypeName;

}
