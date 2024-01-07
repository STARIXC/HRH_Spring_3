package org.utj.hrh.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class FinancialYearDTO {
	
	Integer id;

	private String name;

	private String year;

	private String startDate;

	private String endDate;
	
	private String contractPeriod;

	private String contractNoMonths;
	
	private Integer status;
}
