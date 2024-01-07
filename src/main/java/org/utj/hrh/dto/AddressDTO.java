package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddressDTO {
	private Long addressId; // Can be null for new addresses
	private String street;
	private String postalAddress;
	private String cityTown;
	private String postalCode;
	private String country;
	private Long employeeId;
}
