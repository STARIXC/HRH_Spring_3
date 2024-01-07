package org.utj.hrh.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.utj.hrh.dto.*;
import org.utj.hrh.model.*;
import org.utj.hrh.services.EmployeeFacilityService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MapperConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		// User to Long converter
		Converter<User, Long> userToLongConverter = context -> {
			User source = context.getSource();
			return source != null ? source.getId() : null;
		};
		
		// Employee to Long converter
		Converter<Employee, Long> employeeToLongConverter = context -> {
			Employee source = context.getSource();
			return source != null ? source.getId() : null;
		};
		
		// Leave Policy to Long converter
		Converter<LeavePolicy, Long> leavePolicyToLongConverter = context -> {
			LeavePolicy source = context.getSource();
			return source != null ? source.getId() : null;
		};
		// Employee to Full Name converter
		Converter<Employee, String> employeeToFullNameConverter = context -> {
			Employee source = context.getSource();
			if (source != null) {
				String firstName = source.getFirstName() != null ? source.getFirstName() : "";
				String surname = source.getSurname() != null ? source.getSurname() : "";
				return firstName + " " + surname;
			}
			return null;
		};
		
		// Custom Type Map for LeaveEntitlement to LeaveEntitlementDTO
		modelMapper.typeMap(LeaveEntitlement.class, LeaveEntitlementDTO.class)
				.addMappings(mapper -> {
					mapper.using(employeeToFullNameConverter).map(LeaveEntitlement::getEmployeeLeaveEntitlement, LeaveEntitlementDTO::setEmployeeFullName);
					mapper.map(src -> src.getLeavePolicy().getLeaveType().getLeaveType(), LeaveEntitlementDTO::setLeaveTypeName);
					mapper.using(userToLongConverter).map(LeaveEntitlement::getCreatedBy, LeaveEntitlementDTO::setCreatedBy);
					mapper.using(employeeToLongConverter).map(LeaveEntitlement::getEmployeeLeaveEntitlement, LeaveEntitlementDTO::setEmployeeLeaveEntitlement);
					mapper.using(leavePolicyToLongConverter).map(LeaveEntitlement::getLeavePolicy, LeaveEntitlementDTO::setLeavePolicy);
					// Add other field mappings here
				});
		modelMapper.addMappings(new EmployeeToEmployeeDetailsDTO());
		modelMapper.addConverter(userToLongConverter);
		modelMapper.addConverter(employeeToLongConverter);
		return modelMapper;
	}
	
	private class EmployeeToEmployeeDetailsDTO extends PropertyMap<Employee, EmployeeDetailsDTO> {
		@Override
		protected void configure() {
			using(employeeToBasicInfoDTOConverter).map(source, destination.getBasicInfoDTO());
			using(employeeToStatutoryDetailsDTOConverter).map(source, destination.getStatutoryDetailsDTO());
			using(employeeToContactInfoDTOConverter).map(source, destination.getContactInfoDTO());
			using(employeeToBankingDetailsDTOConverter).map(source, destination.getBankingDetails());
			using(employeeToEmploymentHistoryDTOConverter).map(source, destination.getEmploymentHistoryDTOS());
			using(employeeToEmployeeAcademicQualificationDTOConverter).map(source, destination.getAcademicDetails());
			using(employeeToEmployeeEmergencyContactListDTOConverter).map(source, destination.getEmployeeEmergencyContactDTOS());
			using(employeeToEmployeeDependantsDTOConverter).map(source, destination.getEmployeeDependants());
			using(employeeToEmployeeFacilityDTOConverter).map(source, destination.getEmployeeFacilityDTO());
			

		}
	}
	
	private final Converter<Employee, BasicInfoDTO> employeeToBasicInfoDTOConverter = context -> {
		Employee source = context.getSource();
		BasicInfoDTO basicInfoDTO = new BasicInfoDTO();
		basicInfoDTO.setId(source.getId());
		basicInfoDTO.setPersonNumber(source.getPerson().getPersonNumber());
		basicInfoDTO.setFirstName(source.getFirstName());
		basicInfoDTO.setSurname(source.getSurname());
		basicInfoDTO.setOtherName(source.getOtherName());
		basicInfoDTO.setNationalId(source.getNationalId());
		basicInfoDTO.setGender(source.getGender());
		basicInfoDTO.setDob(source.getDob());
		basicInfoDTO.setMaritalStatus(source.getMaritalStatus());
		basicInfoDTO.setNationality(source.getNationality());
		basicInfoDTO.setDisability(source.getDisability());
		basicInfoDTO.setDisabilityExplain(source.getDisabilityExplain());
		return basicInfoDTO;
	};
	private final Converter<Employee, EmployeeFacilityDTO> employeeToEmployeeFacilityDTOConverter = context -> {
		Employee source = context.getSource();
		EmployeeFacilityDTO employeeFacilityDTO = new EmployeeFacilityDTO();
//		EmployeeFacilityService employeeFacilityService;
//		// Logic to find the active facility for the employee
//		// This might involve a service call or a custom query
//		EmployeeFacility activeFacility = employeeFacilityService.findActiveFacilityForEmployee(source);
//
//		if (activeFacility != null && activeFacility.getFacility() != null) {
//			Facility subPartner = activeFacility.getFacility();
//			employeeFacilityDTO.setSubPartnerId(subPartner.getSubPartnerId());
//			employeeFacilityDTO.setSubPartnerName(subPartner.getSubPartnerName());
//
//			// Assuming Facility has a reference to SubCounty
//			SubCounty district = subPartner.getSubCounty();
//			if (district != null) {
//				employeeFacilityDTO.setDistrictId(district.getDistrictId());
//				employeeFacilityDTO.setDistrictName(district.getDistrictName());
//
//				// Assuming SubCounty has a reference to County
//				County county = district.getCounty();
//				if (county != null) {
//					employeeFacilityDTO.setCountyId(county.getCountyId());
//					employeeFacilityDTO.setCountyName(county.getCountyName());
//				}
//			}
//		}
		return employeeFacilityDTO;
	};

	private final Converter<Employee, StatutoryDetailsDTO> employeeToStatutoryDetailsDTOConverter = context -> {
		Employee source = context.getSource();
		StatutoryDetailsDTO statutoryDetailsDTO = new StatutoryDetailsDTO();
		statutoryDetailsDTO.setKraPin(source.getKraPin());
		statutoryDetailsDTO.setNssfNo(source.getNssfNo());
		statutoryDetailsDTO.setNhifNo(source.getNhifNo());
		statutoryDetailsDTO.setCertGoodConductNo(source.getCertGoodConductNo());
		statutoryDetailsDTO.setHelbClearanceNo(source.getHelbClearanceNo());
		statutoryDetailsDTO.setHelbBeneficiary(source.getHelbBeneficiary());
		return statutoryDetailsDTO;
	};
	private final Converter<Employee, BankingDetailsDTO> employeeToBankingDetailsDTOConverter = context -> {
		Employee source = context.getSource();
		BankingDetailsDTO bankingDetailsDTO = new BankingDetailsDTO();
		bankingDetailsDTO.setBankName(source.getBankName());
		bankingDetailsDTO.setBranch(source.getBranch());
		bankingDetailsDTO.setAccountName(source.getAccountName());
		bankingDetailsDTO.setAccountNumber(source.getAccountNumber());
		
		return bankingDetailsDTO;
	};
	
	private final Converter<Employee, ContactInfoDTO> employeeToContactInfoDTOConverter = context -> {
		Employee source = context.getSource();
		ContactInfoDTO contactInfoDTO = new ContactInfoDTO();
		contactInfoDTO.setPhone(source.getPhone());
		contactInfoDTO.setEmail(source.getEmail());
		contactInfoDTO.setAltPhone(source.getAltPhone());
		contactInfoDTO.setAltEmail(source.getAltEmail());
		// Map presentAddress and homeAddress here
		contactInfoDTO.setPresentAddress(mapAddressToDTO(source.getPresentAddress()));
		contactInfoDTO.setHomeAddress(mapAddressToDTO(source.getHomeAddress()));
		return contactInfoDTO;
	};

	// Implement the mapAddressToDTO method if you need to map addresses
	private AddressDTO mapAddressToDTO(Address address) {
		if (address == null) {
			return null;
		}
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressId(address.getAddress_id());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setPostalAddress(address.getPostalAddress());
		addressDTO.setCityTown(address.getCityTown());
		addressDTO.setPostalCode(address.getPostalCode());
		addressDTO.setCountry(address.getCountry());
		return addressDTO;
	}
	private final Converter<Employee, List<EmploymentHistoryDTO>> employeeToEmploymentHistoryDTOConverter = context -> {
		Employee source = context.getSource();
		if (source.getEmploymentHistory() != null) {
			return source.getEmploymentHistory().stream()
					.map(this::mapEmploymentHistoryToDTO)
					.collect(Collectors.toList());
		}
		return null;
	};

	
	private EmploymentHistoryDTO mapEmploymentHistoryToDTO(EmploymentHistory employmentHistory) {
		EmploymentHistoryDTO dto = new EmploymentHistoryDTO();
		dto.setEmprecordid(employmentHistory.getEmprecordid());
		dto.setFacilityId(Long.valueOf(employmentHistory.getFacility() != null ? employmentHistory.getFacility().getCentreSanteId(): null));
		dto.setEmployeePositionId(Long.valueOf(employmentHistory.getEmployeePosition() != null ? employmentHistory.getEmployeePosition().getId() : null));
		LocalDate startDate = employmentHistory.getDateStarted();
		dto.setDateStarted(startDate);
		LocalDate endDate = employmentHistory.getDateEnded() != null ?
				employmentHistory.getDateEnded() :
				null;
		
		dto.setDateEnded(endDate != null ? endDate: null);
		dto.setDateEnded(employmentHistory.getDateEnded());
		dto.setFinancialYearId(employmentHistory.getFinancialYear() != null ? employmentHistory.getFinancialYear().getId() : null);
		FinancialYear financialYear = employmentHistory.getFinancialYear();
		if (financialYear != null) {
			dto.setFinancialYearId(financialYear.getId());
			dto.setCurrentContract(financialYear.getName());
			dto.setContractPeriod(financialYear.getContractPeriod());
			LocalDate contractEndDate = financialYear.getEndDate();
			dto.setContractEndDate(contractEndDate);
			
			// Calculate expectedMonths
			long expectedMonths = ChronoUnit.MONTHS.between(startDate, contractEndDate);
			dto.setExpectedMonths((int) expectedMonths);
		}
		if (endDate !=null) {
			dto.setMonthsWorked((int) ChronoUnit.MONTHS.between(startDate, endDate));
		} else {
			dto.setMonthsWorked(null); // or appropriate default value
		}
		dto.setIsActive(employmentHistory.getIsActive());
		dto.setCreatedAt(employmentHistory.getCreatedAt());
		dto.setUpdatedAt(employmentHistory.getUpdatedAt());
		return dto;
	}
	private final Converter<Employee, List<EmployeeAcademicQualificationDTO>> employeeToEmployeeAcademicQualificationDTOConverter = context -> {
		Employee source = context.getSource();
		if (source.getEducationList() != null) {
			return source.getEducationList().stream()
					.map(this::mapEmployeeEducationToDTO)
					.collect(Collectors.toList());
		}
		return null;
	};
	
	private final Converter<Employee, List<EmployeeEmergencyContactDTO>> employeeToEmployeeEmergencyContactListDTOConverter = context -> {
		Employee source = context.getSource();
		if (source.getEmployeeEmergencyContacts() != null) {
		return source.getEmployeeEmergencyContacts().stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
		}
		return null;
	};
	private final Converter<Employee, List<DependantDTO>> employeeToEmployeeDependantsDTOConverter = context -> {
		Employee source = context.getSource();
		if (source.getEmployeeDependants() != null) {
			return source.getEmployeeDependants().stream()
					.map(this::convertToDependantDto)
					.collect(Collectors.toList());
		}
		return null;
	};
	
	private DependantDTO convertToDependantDto(Dependant dependant) {
		DependantDTO dto = new DependantDTO();
		dto.setId(dependant.getId());
		dto.setName(dependant.getName());
		dto.setRelationship(dependant.getRelationship());
		dto.setDateOfBirth(dependant.getDateOfBirth());
		dto.setEmployeeDependant(dependant.getEmployeeDependant() !=null ? dependant.getEmployeeDependant().getId() : null);
		return dto;
	}
	
	private EmployeeEmergencyContactDTO mapEmployeeEmergencyContactToDTO(EmployeeEmergencyContact employeeEmergencyContact) {
		EmployeeEmergencyContactDTO dto = new EmployeeEmergencyContactDTO();
		dto.setId(employeeEmergencyContact.getId());
		dto.setEmployeeContact(employeeEmergencyContact.getEmployeeContact() != null ? employeeEmergencyContact.getEmployeeContact().getId() : null);
		dto.setName(employeeEmergencyContact.getName());
		dto.setRelationship(employeeEmergencyContact.getRelationship());
		dto.setHomePhone(employeeEmergencyContact.getHomePhone());
		dto.setMobilePhone(employeeEmergencyContact.getMobilePhone());
		dto.setOfficePhone(employeeEmergencyContact.getOfficePhone());
		return dto;
	}
	private EmployeeEmergencyContact convertToEntity(EmployeeEmergencyContactDTO dto, Employee employee) {
		EmployeeEmergencyContact entity = new EmployeeEmergencyContact();
		// Map fields from DTO to Entity
		entity.setId(dto.getId());
		entity.setEmployeeContact(employee); // Set the employee relationship
		entity.setName(dto.getName());
		entity.setRelationship(dto.getRelationship());
		entity.setHomePhone(dto.getHomePhone());
		entity.setMobilePhone(dto.getMobilePhone());
		entity.setOfficePhone(dto.getOfficePhone());
		return entity;
	}
	
	private EmployeeEmergencyContactDTO convertToDto(EmployeeEmergencyContact entity) {
		EmployeeEmergencyContactDTO dto = new EmployeeEmergencyContactDTO();
		dto.setId(entity.getId());
		dto.setEmployeeContact(entity.getEmployeeContact() != null ? entity.getEmployeeContact().getId() : null);
		dto.setName(entity.getName());
		dto.setRelationship(entity.getRelationship());
		dto.setHomePhone(entity.getHomePhone());
		dto.setMobilePhone(entity.getMobilePhone());
		dto.setOfficePhone(entity.getOfficePhone());
		
		// Assuming you're using ModelMapper or similar tool
		return dto;
	}
	
	
	private EmployeeAcademicQualificationDTO mapEmployeeEducationToDTO(EmployeeEducation employeeEducation) {
		EmployeeAcademicQualificationDTO dto = new EmployeeAcademicQualificationDTO();
		dto.setEducation(employeeEducation.getId());
		dto.setEmployeeId(employeeEducation.getEducation() != null ? employeeEducation.getEducation().getId(): null);
//		dto.setEmployeePositionId(Long.valueOf(employmentHistory.getEmployeePosition() != null ? employmentHistory.getEmployeePosition().getId() : null));
		dto.setQualificationName(employeeEducation.getEducation()!=null ? employeeEducation.getEducation().getName():null);
		dto.setStartDate(employeeEducation.getStartDate());
		dto.setEndDate(employeeEducation.getEndDate());
		dto.setInstitute(employeeEducation.getInstitute());
		dto.setMajor(employeeEducation.getMajor());
		dto.setScore(employeeEducation.getScore());
		dto.setYear(employeeEducation.getYear());
		
		return dto;
	}
	

}
