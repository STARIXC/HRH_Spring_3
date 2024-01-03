package org.utj.hrh.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.utj.hrh.dto.*;
import org.utj.hrh.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MapperConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new EmployeeToEmployeeDetailsDTO());
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
			using(employeeToEmployeeEducationDTOConverter).map(source, destination.getAcademicDetails());

//			using(employeeEmergencyContactDTOConverter).map(source, destination.getEmpEmergencyContact());
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
		basicInfoDTO.setPhone(source.getPhone());
		basicInfoDTO.setEmail(source.getEmail());
		basicInfoDTO.setAltPhone(source.getAltPhone());
		basicInfoDTO.setAltEmail(source.getAltEmail());
		basicInfoDTO.setDob(source.getDob());
		basicInfoDTO.setMaritalStatus(source.getMaritalStatus());
		basicInfoDTO.setNationality(source.getNationality());
		basicInfoDTO.setDisability(source.getDisability());
		basicInfoDTO.setDisabilityExplain(source.getDisabilityExplain());
		return basicInfoDTO;
	};
	
	private final Converter<Employee, StatutoryDetailsDTO> employeeToStatutoryDetailsDTOConverter = context -> {
		Employee source = context.getSource();
		StatutoryDetailsDTO statutoryDetailsDTO = new StatutoryDetailsDTO();
		statutoryDetailsDTO.setNationality(source.getNationality());
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
	private final Converter<EmployeeEmergencyContact, EmployeeEmergencyContactDTO> employeeEmergencyContactDTOConverter = context -> {
		EmployeeEmergencyContact source = context.getSource();
		EmployeeEmergencyContactDTO EmergencyContactDTO = new EmployeeEmergencyContactDTO();
		EmergencyContactDTO.setSeqNo(source.getSeqNo());
		EmergencyContactDTO.setEmployeeId(source.getEmployeeContact() != null ? source.getEmployeeContact().getId() : null);
		EmergencyContactDTO.setName(source.getName());
		EmergencyContactDTO.setRelationship(source.getRelationship());
		EmergencyContactDTO.setHomePhone(source.getHomePhone());
		EmergencyContactDTO.setMobilePhone(source.getMobilePhone());
		EmergencyContactDTO.setOfficePhone(source.getOfficePhone());
		return EmergencyContactDTO;
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
		dto.setDateStarted(employmentHistory.getDateStarted());
		dto.setDateEnded(employmentHistory.getDateEnded());
		dto.setFinancialYear(employmentHistory.getFinancialYear());
		dto.setMonthsWorked(employmentHistory.getMonthsWorked());
		dto.setCurrentContract(employmentHistory.getCurrentContract());
		dto.setContractPeriod(employmentHistory.getContractPeriod());
		dto.setContractEndDate(employmentHistory.getContractEndDate());
		dto.setExpectedMonths(employmentHistory.getExpectedMonths());
		dto.setIsActive(employmentHistory.getIsActive());
		dto.setCreatedAt(employmentHistory.getCreatedAt());
		dto.setUpdatedAt(employmentHistory.getUpdatedAt());
		return dto;
	}
	private final Converter<Employee, List<EmployeeEducationDTO>> employeeToEmployeeEducationDTOConverter = context -> {
		Employee source = context.getSource();
		if (source.getEducationList() != null) {
			return source.getEducationList().stream()
					.map(this::mapEmployeeEducationToDTO)
					.collect(Collectors.toList());
		}
		return null;
	};
	
	private EmployeeEducationDTO mapEmployeeEducationToDTO(EmployeeEducation employeeEducation) {
		EmployeeEducationDTO dto = new EmployeeEducationDTO();
		dto.setEducationId(employeeEducation.getId());
		dto.setEducationId(Long.valueOf(employeeEducation.getEducation() != null ? employeeEducation.getEducation().getId(): null));
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
