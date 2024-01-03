package org.utj.hrh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
public class EmployeeDetailsDTO {
	private BasicInfoDTO basicInfoDTO;
	private StatutoryDetailsDTO statutoryDetailsDTO;
	private ContactInfoDTO contactInfoDTO;
	private EmployeeEmergencyContactDTO empEmergencyContact;
	private BankingDetailsDTO bankingDetails;
	private List<EmploymentHistoryDTO> employmentHistoryDTOS;
	private List<EmployeeEducationDTO> academicDetails;
	//	private List<EmployeeEmergencyContactDTO> employeeEmergencyContacts;
//	private EmploymentDetailsDTO employmentDetailsDTO;
	private List<EmployeeStatusDTO> employeeStatuses;
	private List<EducationLevelDTO> educationLevels;
	private List<CountyDTO> counties;
	private List<DesignationDTO> designations;
	private List<CarderCategoryDTO> categories;
}