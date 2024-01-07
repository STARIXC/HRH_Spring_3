package org.utj.hrh.dto;


import lombok.Data;

import java.time.LocalDate;


@Data
public class LeavePolicyDetailsDTO {
	private Long id;
	private Integer days;
	private String gender;
	private LocalDate fromDate;
	private LocalDate toDate;
}
