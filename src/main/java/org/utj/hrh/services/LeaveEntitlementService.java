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
        Optional<Employee> employeeOptional = employeeRepository.findById(dto.getEmployeeLeaveEntitlement());
        
        if (!employeeOptional.isPresent()) {
            // Handle the case where the employee is not found
            throw new RuntimeException("Employee not found with ID: " + dto.getEmployeeLeaveEntitlement());
        }
        Employee employee = employeeOptional.get();
        
        // Now convert to entity with the employee object
        LeaveEntitlement entitlement = convertToEntity(dto, employee);
        leaveEntitlementRepository.save(entitlement);
    }

//    public void saveAllEmployeesLeaveEntitlement(LeaveEntitlementDTO dto) {
//        List<Employee> allEmployees = employeeRepository.findAll();
//        for (Employee employee : allEmployees) {
//            LeaveEntitlement entitlement = convertToEntity(dto);
//            entitlement.setEmployeeLeaveEntitlement(employee);
//            leaveEntitlementRepository.save(entitlement);
//        }
//    }
//public void saveAllEmployeesLeaveEntitlement(LeaveEntitlementDTO dto) {
//    List<Employee> allEmployees = employeeRepository.findAll();
//    for (Employee employee : allEmployees) {
//        // Check if an entitlement already exists for this employee with the specified policy and date range
//        boolean entitlementExists = leaveEntitlementRepository.existsByEmployeeLeaveEntitlement_IdAndLeavePolicy_IdAndFromDateAndToDate(
//                employee.getId(), dto.getLeavePolicy(), dto.getFromDate(), dto.getToDate());
//
//        if (!entitlementExists) {
//            LeaveEntitlement entitlement = convertToEntity(dto, employee);
//            leaveEntitlementRepository.save(entitlement);
//        }
//    }
//}
    
    public void saveAllEmployeesLeaveEntitlement(LeaveEntitlementDTO dto) {
        // Fetch the leave policy based on the ID
        LeavePolicy leavePolicy = leavePolicyRepository.findById(dto.getLeavePolicy()).orElse(null);
        
        // Check if leave policy is applicable to all genders or a specific gender
        boolean isPolicyForAllGenders = leavePolicy != null && leavePolicy.getGender().equals("-1");
        
        List<Employee> employees;
        if (isPolicyForAllGenders) {
            // If policy applies to all genders, get all employees
            employees = employeeRepository.findAll();
        } else {
            // If policy applies to a specific gender, filter employees by gender
            employees = employeeRepository.findByGender(leavePolicy.getGender().toUpperCase());
        }
        
        for (Employee employee : employees) {
            // Check if an entitlement already exists for this employee with the specified policy and date range
            boolean entitlementExists = leaveEntitlementRepository.existsByEmployeeLeaveEntitlement_IdAndLeavePolicy_IdAndFromDateAndToDate(
                    employee.getId(), dto.getLeavePolicy(), dto.getFromDate(), dto.getToDate());
            
            if (!entitlementExists) {
                LeaveEntitlement entitlement = convertToEntity(dto, employee);
                leaveEntitlementRepository.save(entitlement);
            }
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
    private LeaveEntitlement convertToEntity(LeaveEntitlementDTO dto, Employee employee) {
        // Conversion logic
        LeaveEntitlement entitlement = new LeaveEntitlement();
        
        // Use the passed employee object directly
        entitlement.setEmployeeLeaveEntitlement(employee);
        
        Optional<LeavePolicy> leavePolicyOptional = leavePolicyRepository.findById(dto.getLeavePolicy());
        if (!leavePolicyOptional.isPresent()) {
            // Handle the case where leave policy is not found
            throw new RuntimeException("Leave policy not found with ID: " + dto.getLeavePolicy());
        }
        LeavePolicy leavePolicy = leavePolicyOptional.get();
        
        // Populate fields from dto to entitlement
        entitlement.setId(dto.getId());
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
            User user = userRepository.findByUsername(username);
            if (user != null) {
                entitlement.setCreatedBy(user);
            } else {
                throw new RuntimeException("Logged in user not found in database");
            }
        } else {
            // Handle the case where the user details are not available
            throw new RuntimeException("User not logged in");
        }
        
        entitlement.setCreatedAt(LocalDateTime.now());
        entitlement.setNote(dto.getNote());
        
        return entitlement;
    }

//    private LeaveEntitlement convertToEntity(LeaveEntitlementDTO dto) {
//        // Conversion logic
//        LeaveEntitlement entitlement = new LeaveEntitlement();
//
//        Employee employee = employeeRepository.findById(dto.getEmployeeLeaveEntitlement()).get();
//        LeavePolicy leavePolicy = leavePolicyRepository.findById(dto.getLeavePolicy()).get();
//        // Populate fields from dto to entitlement
//        entitlement.setId(dto.getId());
//        entitlement.setEmployeeLeaveEntitlement(employee);
//        entitlement.setNoDays(dto.getNoDays());
//        entitlement.setDaysUsed(dto.getDaysUsed());
//        entitlement.setLeavePolicy(leavePolicy);
//        entitlement.setFromDate(dto.getFromDate());
//        entitlement.setToDate(dto.getToDate());
//        entitlement.setCreditedDate(LocalDate.now());
//        // Set createdBy from the logged-in user's details
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails) principal).getUsername();
//            // Assuming you have a method to get user ID by username
//            User userId = userRepository.findByUsername(username);
//            entitlement.setCreatedBy(userId);
//        } else {
//            // Handle the case where the user details are not available
//            throw new RuntimeException("User not logged in");
//        }
//        entitlement.setCreatedAt(LocalDateTime.now());
//        entitlement.setNote(dto.getNote());
//
//
//        return entitlement;
//    }
}
