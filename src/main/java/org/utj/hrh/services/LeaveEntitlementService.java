package org.utj.hrh.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.LeaveEntitlementDTO;
import org.utj.hrh.model.*;
import org.utj.hrh.repository.EmployeeRepository;
import org.utj.hrh.repository.LeaveEntitlementRepository;
import org.utj.hrh.repository.LeavePolicyRepository;
import org.utj.hrh.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaveEntitlementService {
    private final LeaveEntitlementRepository leaveEntitlementRepository;
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final LeavePolicyRepository leavePolicyRepository;
    private final UserRepository userRepository;
    @Autowired
    public LeaveEntitlementService(LeaveEntitlementRepository leaveEntitlementRepository, ModelMapper modelMapper, EmployeeRepository employeeRepository, LeavePolicyRepository leavePolicyRepository, UserRepository userRepository) {
        this.leaveEntitlementRepository = leaveEntitlementRepository;
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.leavePolicyRepository = leavePolicyRepository;
        this.userRepository = userRepository;
    }
    
    public void saveIndividualLeaveEntitlement(LeaveEntitlementDTO dto) {
        LeaveEntitlement entitlement = convertToEntity(dto);
        leaveEntitlementRepository.save(entitlement);
    }
    
    public void saveAllEmployeesLeaveEntitlement(LeaveEntitlementDTO dto) {
        List<Employee> allEmployees = employeeRepository.findAll();
        for (Employee employee : allEmployees) {
            LeaveEntitlement entitlement = convertToEntity(dto);
            entitlement.setEmployeeLeaveEntitlement(employee);
            leaveEntitlementRepository.save(entitlement);
        }
    }
 

    public LeaveEntitlement getEntitlement(Integer id) throws EntityNotFoundException {
        try{
            return leaveEntitlementRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException("could not find Policy with  ID :"+id);
        }

    }
    public void delete(Integer id) throws EntityNotFoundException{
        Long countById= leaveEntitlementRepository.countById(id);
        if (countById==null || countById==0){
            throw new EntityNotFoundException("could not find any Policy with ID :"+id);
        }
        leaveEntitlementRepository.deleteById(id);
    }
    public LeaveEntitlementDTO createOrUpdateLeaveEntitlement(LeaveEntitlementDTO dto) {
        LeaveEntitlement leaveEntitlement = modelMapper.map(dto, LeaveEntitlement.class);
        leaveEntitlement = leaveEntitlementRepository.save(leaveEntitlement);
        return modelMapper.map(leaveEntitlement, LeaveEntitlementDTO.class);
    }
    
    public List<LeaveEntitlementDTO> getAllLeaveEntitlements() {
        return leaveEntitlementRepository.findAll().stream()
                .map(entitlement -> modelMapper.map(entitlement, LeaveEntitlementDTO.class))
                .collect(Collectors.toList());
    }
    
    public LeaveEntitlementDTO getLeaveEntitlementById(Integer id) {
        Optional<LeaveEntitlement> entitlement = leaveEntitlementRepository.findById(id);
        return entitlement.map(value -> modelMapper.map(value, LeaveEntitlementDTO.class)).orElse(null);
    }
    
    public void deleteLeaveEntitlement(Integer id) {
        leaveEntitlementRepository.deleteById(id);
    }
    
    public List<LeaveEntitlementDTO> getLeaveEntitlementsByEmployeeId(Long employeeId) {
        List<LeaveEntitlement> entitlements = leaveEntitlementRepository.findByEmployeeLeaveEntitlementId(employeeId);
        return entitlements.stream()
                .map(entitlement -> modelMapper.map(entitlement, LeaveEntitlementDTO.class))
                .collect(Collectors.toList());
    }
    
    private LeaveEntitlement convertToEntity(LeaveEntitlementDTO dto) {
        // Conversion logic
        LeaveEntitlement entitlement = new LeaveEntitlement();
        Employee employee = employeeRepository.findById(dto.getEmployeeLeaveEntitlement()).get();
        LeavePolicy leavePolicy = leavePolicyRepository.findById(dto.getLeavePolicy()).get();
        // Populate fields from dto to entitlement
        entitlement.setId(dto.getId());
        entitlement.setEmployeeLeaveEntitlement(employee);
        entitlement.setNoDays(dto.getNoDays());
        entitlement.setDaysUsed(dto.getDaysUsed());
        entitlement.setLeavePolicy(leavePolicy);
        entitlement.setFromDate(dto.getFromDate());
        entitlement.setToDate(dto.getToDate());
        entitlement.setCreditedDate(LocalDate.now());
        // Set createdBy from the logged-in user's details
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            // Assuming you have a method to get user ID by username
            User userId = userRepository.findByUsername(username);
            entitlement.setCreatedBy(userId);
        } else {
            // Handle the case where the user details are not available
            throw new RuntimeException("User not logged in");
        }
        entitlement.setCreatedAt(LocalDateTime.now());
        entitlement.setNote(dto.getNote());
   
        
        return entitlement;
    }
}
