package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BankingDetailsDTO {
	private String bankName;
	private String branch;
	private String accountName;
	private String accountNumber;
}
