package org.utj.hrh.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.EmployeeAcademicQualificationDTO;
import org.utj.hrh.dto.EmployeeEmergencyContactDTO;
import org.utj.hrh.model.*;
import org.utj.hrh.repository.EmployeeEducationRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeEducationQualificationService {
  
  private final EmployeeEducationRepository employeeEducationRepository;
  private final EmployeeService employeeService;
  private final EducationLevelService educationLevelService;
  
  @Autowired
  public EmployeeEducationQualificationService(EmployeeEducationRepository employeeEducationRepository, EmployeeService employeeService, EducationLevelService educationLevelService) {
    this.employeeEducationRepository = employeeEducationRepository;
      this.employeeService = employeeService;
      this.educationLevelService = educationLevelService;
  }
    public EmployeeEducation convertDTOtoEntity(EmployeeAcademicQualificationDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, EmployeeEducation.class);
    }
    public List<EmployeeEducation> getAll(){
        return  employeeEducationRepository.findAll();
    }
    public void save(EmployeeEducation employeeEducation) {
        boolean isUpdatingEmployeeEducation = (employeeEducation.getId() !=null);

        employeeEducationRepository.save(employeeEducation);
    }

    public EmployeeEducation getEmployeeEducation(Long  id) throws EntityNotFoundException {
        try{
            return employeeEducationRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException("could not find any Employee with ID :"+id);
        }

    }
    public void delete(Long id) throws  EmployeeNotFoundException {
        Long countById= employeeEducationRepository.countById(id);
        if (countById==null || countById==0){
            throw new EmployeeNotFoundException("could not find any Employee with ID :"+id);
        }
        employeeEducationRepository.deleteById(id);
    }
    
    
    // Service method to find academic qualifications by person number
    public List<EmployeeEducation> findAcademicQualificationsByPersonNumber(String personNumber) {
        return employeeEducationRepository.findByAcademicQualification_Person_PersonNumber(personNumber);
    }
    

    public void updateEducationQualificationInfo(Long employeeId, EmployeeAcademicQualificationDTO employeeAcademicQualificationDTO) {
        EmployeeEducation education;
        EducationLevel educationLevel=educationLevelService.findEducationById(employeeAcademicQualificationDTO.getEducation());
        Employee employee = employeeService.findEmployeeById(employeeId);
        education = manualConvertDTOtoEntity(employeeAcademicQualificationDTO,employee,educationLevel);
        employeeEducationRepository.save(education);
        
    }
    
    public EmployeeEducation manualConvertDTOtoEntity(EmployeeAcademicQualificationDTO dto, Employee employee, EducationLevel educationLevel) {
        EmployeeEducation entity = new EmployeeEducation();
        entity.setId(dto.getId());
        entity.setAcademicQualification(employee);
        entity.setEducation(educationLevel);
        entity.setInstitute(dto.getInstitute());
        entity.setMajor(dto.getMajor());
        entity.setYear(dto.getYear());
        entity.setScore(dto.getScore());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        
        return entity;
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
}
