package org.utj.hrh.dto;


import lombok.Data;


@Data
public class LeavePolicyDetailsDTO {
	private Long id;
	private Integer days;
	private String gender;
	private String fromDate;
	private String toDate;
}
