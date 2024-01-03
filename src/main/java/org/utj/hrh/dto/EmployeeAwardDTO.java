package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAwardDTO {
	private Long employeeAwardId;
	private String awardName;
	private String giftItem;
	private String month;
	private Long employeeId; // Replacing the direct Employee reference
}
