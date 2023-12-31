package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ContactInfoDTO {
	
	private String phone;
	private String email;
	private String altPhone;
	private String altEmail;
	private AddressDTO presentAddress;
	private AddressDTO homeAddress;
	private Long employeeId;
	
	
}
