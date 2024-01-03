package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEducationDTO {
	private Long id;
	private Long employeeId; // Replacing the direct Employee reference
	private Long educationId; // Replacing the direct Education reference
	private String institute;
	private String qualificationName;
	private String major;
	private Integer year;
	private String score;
	private LocalDate startDate;
	private LocalDate endDate;
}
