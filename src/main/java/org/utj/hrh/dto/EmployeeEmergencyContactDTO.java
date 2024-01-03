package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
public class EmployeeEmergencyContactDTO {
	private BigDecimal seqNo;
	private Long employeeId; // Replacing the direct Employee reference
	private String name;
	private String relationship;
	private String homePhone;
	private String mobilePhone;
	private String officePhone;
}
