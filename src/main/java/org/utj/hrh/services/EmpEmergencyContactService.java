package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.EmployeeEmergencyContactDTO;
import org.utj.hrh.dto.EmployeeHistoryPositionFacilityDTO;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmployeeEmergencyContact;
import org.utj.hrh.repository.EmpEmergencyContactRepository;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmpEmergencyContactService {
  
  private final EmpEmergencyContactRepository empEmergencyContactRepository;
  private final EmployeeService employeeService;
  
  
  @Autowired
  public EmpEmergencyContactService(EmpEmergencyContactRepository empEmergencyContactRepository, EmployeeService employeeService) {
    this.empEmergencyContactRepository = empEmergencyContactRepository;
      this.employeeService = employeeService;
  }
  
    public List<EmployeeEmergencyContact> getAll(){
        return  empEmergencyContactRepository.findAll();
    }

    public List<EmployeeEmergencyContact> getEmployeeEmergencyContact(Long id) throws EntityNotFoundException {
        try{
            Employee employee=employeeService.findEmployeeById(id);
            
            return empEmergencyContactRepository.findByEmployeeContact(employee);
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException("could not find any EmployeeContact Record with ID :"+id);
        }

    }
    public void delete(Long id) throws  EntityNotFoundException {
        Long countById= empEmergencyContactRepository.countById(id);
        if (countById==null || countById==0){
            throw new EntityNotFoundException("could not find any EmployeeContact Record with ID :"+id);
        }
        empEmergencyContactRepository.deleteById(id);
    }
  
    public void updateEmergencyContactInfo(Long employeeId, EmployeeEmergencyContactDTO employeeEmergencyContactDTO) throws EntityNotFoundException {
        EmployeeEmergencyContact employeeEmergencyContact;
        Employee employee = employeeService.findEmployeeById(employeeId);
        EmployeeEmergencyContact entity = manualConvertDTOtoEntity(employeeEmergencyContactDTO,employee);
        empEmergencyContactRepository.save(entity);
        

    }
    
    

    
    
    public EmployeeEmergencyContact manualConvertDTOtoEntity(EmployeeEmergencyContactDTO dto, Employee employee) {
        EmployeeEmergencyContact entity = new EmployeeEmergencyContact();
        entity.setId(dto.getId());
        entity.setEmployeeContact(employee);
        entity.setName(dto.getName());
        entity.setRelationship(dto.getRelationship());
        entity.setHomePhone(dto.getHomePhone());
        entity.setMobilePhone(dto.getMobilePhone());
        entity.setOfficePhone(dto.getOfficePhone());
        return entity;
    }
    
    
    public EmployeeEmergencyContactDTO getEmergencyContactById(Long id) throws EntityNotFoundException {
        try{
            EmployeeEmergencyContact employeeEmergencyContact=empEmergencyContactRepository.findById(id).get();
            return convertToDto(employeeEmergencyContact);
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException("could not find any EmployeeEmergencyContact Record with ID :"+id);
        }
    }
    
    public EmployeeEmergencyContactDTO convertToDto(EmployeeEmergencyContact entity) {
        EmployeeEmergencyContactDTO dto = new EmployeeEmergencyContactDTO();
        dto.setId(entity.getId());
        dto.setEmployeeContact(entity.getEmployeeContact() != null ? entity.getEmployeeContact().getId() : null);
        dto.setName(entity.getName());
        dto.setRelationship(entity.getRelationship());
        dto.setHomePhone(entity.getHomePhone());
        dto.setMobilePhone(entity.getMobilePhone());
        dto.setOfficePhone(entity.getOfficePhone());
        return dto;
    }
	
	public void updateJobInfo(Long employeeId, EmployeeHistoryPositionFacilityDTO employeeHistoryPositionFacilityDTO) {
	}
}
