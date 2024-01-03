package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLicenseDTO {
	private Long licenseId; // Replacing the direct License reference
	private String licenseNo;
	private Date licenseIssuedDate;
	private Date licenseExpiryDate;
	private Long employeeId; // Replacing the direct Employee reference
}
