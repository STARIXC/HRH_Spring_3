package org.utj.hrh.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.utj.hrh.dto.BasicInfoDTO;
import org.utj.hrh.dto.ContactInfoDTO;
import org.utj.hrh.dto.EmployeeDetailsDTO;
import org.utj.hrh.model.Employee;


import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EmployeeDetailsMapper {
	EmployeeDetailsMapper INSTANCE = Mappers.getMapper(EmployeeDetailsMapper.class);
	EmployeeDetailsDTO employeeToEmployeeDetailsDTO(Optional<Employee> employee);
	
	// Additional mappings for individual DTOs if needed
	// For example:
	BasicInfoDTO basicInfoDto(Employee employee);
	ContactInfoDTO contactInfoToDTO(Employee employee);

}
