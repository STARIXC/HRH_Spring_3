package org.utj.hrh.dto;

import lombok.*;

@Data
public class BasicInfoDTO {
	private Long id; // Corresponding to Employee's id
	private String personNumber; // From the Person entity linked to the Employee
	private String firstName;
	private String surname;
	private String otherName;
	private String nationalId;
	private String gender;
	private String phone;
	private String email;
	private String altPhone;
	private String altEmail;
	private String dob; // Date of birth
	private String maritalStatus;
	private String nationality;
	private String disability;
	private String disabilityExplain;
	
}
