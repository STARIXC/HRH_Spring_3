package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utj.hrh.model.Designation;
import org.utj.hrh.model.EmployeeStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentDetailsDTO {
	private String dateStarted;
	private String dateEnded;
	private EmployeeStatus status;
	private Designation position;
}
