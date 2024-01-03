package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.EmployeeEmergencyContactDTO;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.EmployeeEmergencyContact;
import org.utj.hrh.repository.EmpEmergencyContactRepository;

import java.math.BigDecimal;
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
//    public void save(EmployeeEmergencyContactDTO employeeEmergencyContact) {
//        boolean isUpdatingEmployeeEmergencyContact = (employeeEmergencyContact.getSeqNo() !=null);
//
//        empEmergencyContactRepository.save(employeeEmergencyContact);
//    }

    public EmployeeEmergencyContact getEmployeeEmergencyContact(BigDecimal id) throws EntityNotFoundException {
        try{
            return empEmergencyContactRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException("could not find any EmployeeContact Record with ID :"+id);
        }

    }
    public void delete(BigDecimal id) throws  EntityNotFoundException {
        Long countById= empEmergencyContactRepository.countBySeqNo(id);
        if (countById==null || countById==0){
            throw new EntityNotFoundException("could not find any EmployeeContact Record with ID :"+id);
        }
        empEmergencyContactRepository.deleteById(id);
    }
    
    
    // Service method to find academic qualifications by person number
    public Optional<Employee> findByEmergencyContact_Person_PersonNumber(String personNumber) {
        return empEmergencyContactRepository.findByEmpNoWithContacts(personNumber);
    }
    
    
    
    public void saveEmployeeEmergencyContact(EmployeeEmergencyContactDTO dto) {
        EmployeeEmergencyContact entity = manualConvertDTOtoEntity(dto);
        empEmergencyContactRepository.save(entity);
    }
    
    
    public EmployeeEmergencyContact manualConvertDTOtoEntity(EmployeeEmergencyContactDTO dto) {
        EmployeeEmergencyContact entity = new EmployeeEmergencyContact();
        entity.setSeqNo(dto.getSeqNo());
        Employee employee = employeeService.findEmployeeById(dto.getEmployeeId());
        entity.setEmployeeContact(employee);
        entity.setRelationship(dto.getRelationship());
        entity.setHomePhone(dto.getHomePhone());
        entity.setMobilePhone(dto.getMobilePhone());
        entity.setOfficePhone(dto.getOfficePhone());
        return entity;
    }
}
