package org.utj.hrh.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDetailsDTO {
	private BasicInfoDTO basicInfoDTO;
	private StatutoryDetailsDTO statutoryDetailsDTO;
	private ContactInfoDTO contactInfoDTO;
	private EmployeeEmergencyContactDTO empEmergencyContact = new EmployeeEmergencyContactDTO();
	private BankingDetailsDTO bankingDetails;
	private EmployeeAcademicQualificationDTO academicDetailsDTO;
	private DependantDTO dependantDTO;
	private EmployeeFacilityDTO employeeFacilityDTO;
	private EmploymentHistoryDTO employmentHistoryDTO;
	private List<EmploymentHistoryDTO> employmentHistoryDTOS;
	private List<EmployeeAcademicQualificationDTO> academicDetails;
	private List<EmployeeStatusDTO> employeeStatuses;
	private List<EducationLevelDTO> educationLevels;
	private List<EmployeeEmergencyContactDTO> employeeEmergencyContactDTOS;
	private List<CountyDTO> counties;
	private List<DesignationDTO> designations;
	private List<CarderCategoryDTO> categories;
	private List<DependantDTO> employeeDependants;
	private List<FinancialYearDTO> financialYearDOS;
}