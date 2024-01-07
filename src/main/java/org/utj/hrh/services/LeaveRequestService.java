package org.utj.hrh.services;

import org.utj.hrh.dto.LeaveRequestDTO;
import org.utj.hrh.model.Employee;
import org.utj.hrh.model.LeaveRequest;
import org.utj.hrh.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestService {
    
    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeService employeeService;
    
    @Autowired
    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository, EmployeeService employeeService) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeService = employeeService;
    }
    
    public List<LeaveRequestDTO> getAllLeaveRequests() {
        return leaveRequestRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public LeaveRequestDTO getLeaveRequestById(Integer id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        return convertToDTO(leaveRequest);
    }
    
    public LeaveRequestDTO createLeaveRequest(LeaveRequestDTO leaveRequestDTO) {
        LeaveRequest leaveRequest = convertToEntity(leaveRequestDTO);
        leaveRequest = leaveRequestRepository.save(leaveRequest);
        return convertToDTO(leaveRequest);
    }
    
    private LeaveRequest convertToEntity(LeaveRequestDTO dto) {
        Employee employee= employeeService.findEmployeeById(dto.getEmployeeId());
        // Conversion logic from DTO to Entity
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setApplication_id(dto.getApplicationId());
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());
        leaveRequest.setNumDays(dto.getNumDays());
        leaveRequest.setApplicationDate(dto.getApplicationDate());
        leaveRequest.setEmployee(employee);
  
        // ...
        return leaveRequest; // Placeholder
    }
    
    private LeaveRequestDTO convertToDTO(LeaveRequest leaveRequest) {
        // Conversion logic from Entity to DTO
        LeaveRequestDTO dto = new LeaveRequestDTO();
        dto.setApplicationId(leaveRequest.getApplication_id());
        dto.setStartDate(leaveRequest.getStartDate());
        dto.setEndDate(leaveRequest.getEndDate());
        dto.setNumDays(leaveRequest.getNumDays());
        dto.setApplicationDate(leaveRequest.getApplicationDate());
        dto.setEmployeeId(leaveRequest.getEmployee().getId());
        
        // ...
        return dto; // Placeholder
    }
}
