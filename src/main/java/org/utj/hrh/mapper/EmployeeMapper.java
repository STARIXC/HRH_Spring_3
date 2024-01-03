package org.utj.hrh.mapper;

import org.mapstruct.Mapper;
import org.utj.hrh.dto.StaffDTO;
import org.utj.hrh.model.Employee;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	StaffDTO employeeToEmployeeDTO(Employee employee);
	
	Employee employeeDTOToEmployee(StaffDTO employeeDTO);
	
	List<StaffDTO> employeeListToEmployeeDTOList(List<Employee> employeeList);
	
}
