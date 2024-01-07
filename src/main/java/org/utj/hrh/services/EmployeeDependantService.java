package org.utj.hrh.services;

import org.springframework.stereotype.Service;
import org.utj.hrh.dto.DependantDTO;
import org.utj.hrh.model.Dependant;
import org.utj.hrh.model.Employee;
import org.utj.hrh.repository.EmployeeDependantRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeDependantService {
	private final EmployeeDependantRepository employeeDependantRepository;
	private final EmployeeService employeeService;
	public EmployeeDependantService(EmployeeDependantRepository employeeDependantRepository, EmployeeService employeeService) {
		this.employeeDependantRepository = employeeDependantRepository;
		this.employeeService = employeeService;
	}
	
	public DependantDTO getDependantById(Long id) throws EntityNotFoundException {
		try{
			Dependant dependant=employeeDependantRepository.findById(id).get();
			return convertToDto(dependant);
		}catch (NoSuchElementException ex){
			throw new EntityNotFoundException("could not find any EmployeeEmergencyContact Record with ID :"+id);
		}
	}
	
	private DependantDTO convertToDto(Dependant dependant) {
		DependantDTO dependantDTO=new DependantDTO();
		dependantDTO.setId(dependant.getId());
		dependantDTO.setName(dependant.getName());
		dependantDTO.setRelationship(dependant.getRelationship());
		dependantDTO.setDateOfBirth(dependant.getDateOfBirth());
		return dependantDTO;
	}
	
	
	public void updateDependantRecord(Long employeeId, DependantDTO dependantDTO) throws EntityNotFoundException {
		Dependant dependant;
		Employee employee = employeeService.findEmployeeById(employeeId);
		dependant = manualConvertDTOtoEntity(dependantDTO,employee);
		employeeDependantRepository.save(dependant);
		
		
	}
	
	private Dependant manualConvertDTOtoEntity(DependantDTO dependantDTO, Employee employee) {
		Dependant dependant=new Dependant();
		dependant.setId(dependantDTO.getId());
		dependant.setName(dependantDTO.getName());
		dependant.setRelationship(dependantDTO.getRelationship());
		dependant.setDateOfBirth(dependantDTO.getDateOfBirth());
		dependant.setEmployeeDependant(employee);
		return dependant;
	}
	
	public List<Dependant> getEmployeeDependants(Long id) throws EntityNotFoundException {
		try{
			Employee employee=employeeService.findEmployeeById(id);
			
			return employeeDependantRepository.findByEmployeeDependant(employee);
		}catch (NoSuchElementException ex){
			throw new EntityNotFoundException("could not find any EmployeeContact Record with ID :"+id);
		}
		
	}
}
