package org.utj.hrh.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.*;
import org.utj.hrh.mapper.EmployeeDetailsMapper;
import org.utj.hrh.model.*;
import org.utj.hrh.repository.EmployeeFacilityRepository;
import org.utj.hrh.repository.EmployeeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	private final EmployeeFacilityRepository employeeFacilityRepository;
	private final ModelMapper modelMapper;
	private final EmployeeDetailsMapper employeeDetailsMapper;
	private final SupervisorService supervisorService;
	private final EducationService educationService;
	private final EmployeeStatusService employeeStatusService;
	private final DesignationService designationService;
	private final CarderCategoryService carderCategoryService;
	private final CountyService countyService;
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeFacilityRepository employeeFacilityRepository, ModelMapper modelMapper, EmployeeDetailsMapper employeeDetailsMapper, SupervisorService supervisorService, EducationService educationService, EmployeeStatusService employeeStatusService, DesignationService designationService, CarderCategoryService carderCategoryService, CountyService countyService) {
		this.employeeRepository = employeeRepository;
		this.employeeFacilityRepository = employeeFacilityRepository;
		this.modelMapper = modelMapper;
		this.employeeDetailsMapper = employeeDetailsMapper;
		this.supervisorService = supervisorService;
		this.educationService = educationService;
		this.employeeStatusService = employeeStatusService;
		this.designationService = designationService;
		this.carderCategoryService = carderCategoryService;
		this.countyService = countyService;
	}
	
	public List<StaffDTO> getAll() {
		return employeeRepository.findAll()
				.stream().
				map(this::convertEntityDTO)
				.collect(Collectors.toList());
	}
	
	//    private StaffDTO convertEntityDTO(Employee employee){
//        StaffDTO staffDTO = new StaffDTO();
//        staffDTO.setId(employee.getId());
//        staffDTO.setPersonNumber(employee.getPerson().getPersonNumber());
//        staffDTO.setFirstName(employee.getFirstName());
//        staffDTO.setSurname(employee.getSurname());
//        staffDTO.setOtherName(employee.getOtherName());
//        staffDTO.setGender(employee.getGender());
//        staffDTO.setPhone(employee.getPhone());
//        staffDTO.setEmail(employee.getEmail());
//
//        Designation position = employee.getPosition();
//        if (position != null) {
//            // Safe to call getPosition_title
//            String title = position.getPosition_title();
//            staffDTO.setPosition_title(title);
//        } else {
//            String title = "Not Defined";
//            staffDTO.setPosition_title(title);
//        }
//
//        return staffDTO;
//
//    }
	private StaffDTO convertEntityDTO(Employee employee) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		StaffDTO staffDTO = new StaffDTO();
        staffDTO = modelMapper.map(employee, StaffDTO.class);
		return staffDTO;
		
	}
	
	public void save(Employee employee) {
		boolean isUpdatingEmployee = (employee.getId() != null);
		
		employeeRepository.save(employee);
	}

//    public Employee getEmployee(String id) throws EmployeeNotFoundException {
//        return employeeRepository.findByPersonPersonNumber(id)
//                .orElseThrow(() -> new EmployeeNotFoundException("could not find any Employee with ID :"+id));
//    }
	
	public void delete(Long id) throws EmployeeNotFoundException {
		Long countById = employeeRepository.countById(id);
		if (countById == null || countById == 0) {
			throw new EmployeeNotFoundException("could not find any Employee with ID :" + id);
		}
		employeeRepository.deleteById(id);
	}
	
	public EmployeeFacility getEmployeeFacilityByEmpNo(String empNo) throws EntityNotFoundException {
		return employeeFacilityRepository.findByEmployee_Person_PersonNumber(empNo)
				.orElseThrow(() -> new EntityNotFoundException("EmployeeFacility not found for person number: " + empNo));
		
	}
	
	
	public List<StaffDTO> getByGender(String gender) {
		List<Employee> employees;
		if (gender == null || "-1".equals(gender)) {
			employees = employeeRepository.findAll();
		} else {
			employees = employeeRepository.findByGender(gender.toUpperCase());
		}
		return employees.stream()
				.map(this::convertToStaffDTO)
				.collect(Collectors.toList());
//		// Check if gender is null or empty
//		if (gender == null || gender.trim().isEmpty()) {
//			// Handle the case where gender is not provided
//			// Could return an empty list or throw an exception
//			return Collections.emptyList();
//		}
//
//		// Convert gender to a consistent case format if needed
//		// This is important if the database values are case-sensitive
//		String genderFormatted = gender.trim().toLowerCase();
//
//		// Fetch and return the list of employees
//		return employeeRepository.findByGender(genderFormatted);
		
	}
	
	public List<StaffDTO> getEmployeesByPolicy(LeavePolicy policy) {
		String gender = policy.getGender();
		List<Employee> employees;
		if (gender == null || "-1".equals(gender)) {
			employees = employeeRepository.findAll();
		} else {
			employees = employeeRepository.findByGender(gender.toUpperCase());
		}
		return employees.stream()
				.map(this::convertToStaffDTO)
				.collect(Collectors.toList());
	}
	
//	public EmployeeDetailsDTO getEmployeeDetails(Long id) {
//
//		Employee employee = employeeRepository.findById(id).get();
//		//return UserMapper.mapToUserDto(user);
//		return modelMapper.map(employee, EmployeeDetailsDTO.class);
//
//	}
public EmployeeDetailsDTO getEmployeeDetails(Long id) {
	// Find the employee by ID
	Optional<Employee> employeeOptional = employeeRepository.findById(id);
	EmployeeDetailsDTO employeeDetailsDTO;
	
	if (employeeOptional.isPresent()) {
		// Map the found employee to EmployeeDetailsDTO
		employeeDetailsDTO= modelMapper.map(employeeOptional.get(), EmployeeDetailsDTO.class);
		
		// Fetch and set unrelated data
		List<EmployeeStatusDTO> employeeStatuses = employeeStatusService.getAll() // Fetching logic
				.stream()
				.map(status -> convertToEmployeeStatusDTO(status)) // Conversion logic
				.collect(Collectors.toList());
//		fetch education Levels
		List<EducationLevelDTO> educationLevelDTOS = educationService.getAll() // Fetching logic
				.stream()
				.map(level -> convertToEducationLevelDTO(level)) // Conversion logic
				.collect(Collectors.toList());
		//		fetch education Levels
		List<DesignationDTO> designationDTOS = designationService.getAll() // Fetching logic
				.stream()
				.map(designation -> convertToDesignationDTO(designation)) // Conversion logic
				.collect(Collectors.toList());
		//		fetch Counties
		List<CountyDTO> countyDTOS = countyService.getAll() // Fetching logic
				.stream()
				.map(county -> convertToCountyDTO(county)) // Conversion logic
				.collect(Collectors.toList());	//		fetch Counties
		List<CarderCategoryDTO> carderCategoryDTOS = carderCategoryService.getAll() // Fetching logic
				.stream()
				.map(carderCategory -> convertToCarderCategoryDTO(carderCategory)) // Conversion logic
				.collect(Collectors.toList());
		// Load and set unrelated lists
		employeeDetailsDTO.setEmployeeStatuses(employeeStatuses);
		employeeDetailsDTO.setEducationLevels(educationLevelDTOS);
		employeeDetailsDTO.setDesignations(designationDTOS);
		employeeDetailsDTO.setCounties(countyDTOS);
		employeeDetailsDTO.setCategories(carderCategoryDTOS);
		return employeeDetailsDTO;
	} else {
		// Handle the case where the employee is not found
		// This could be returning null, throwing an exception, or any other business logic
		throw new RuntimeException("Employee not found with id: " + id);
	}
}
	
	private DesignationDTO convertToDesignationDTO(Designation designation) {
		return modelMapper.map(designation, DesignationDTO.class);
	}
	
	private EducationLevelDTO convertToEducationLevelDTO(EducationLevel level) {
		return modelMapper.map(level, EducationLevelDTO.class);
	}
	
	private EmployeeStatusDTO  convertToEmployeeStatusDTO(EmployeeStatus status) {
		return modelMapper.map(status, EmployeeStatusDTO.class);
	}
	
	private CountyDTO  convertToCountyDTO(County county) {
		return modelMapper.map(county, CountyDTO.class);
	}private CarderCategoryDTO  convertToCarderCategoryDTO(CarderCategory carderCategory) {
		return modelMapper.map(carderCategory, CarderCategoryDTO.class);
	}
	
	public Employee findEmployeeById(Long employeeId) {
		return employeeRepository.findById(employeeId).orElse(null);
	}
	
	public List<StaffDTO> getEmployeesByGenderPolicy(LeavePolicy policy) {
		String gender = policy.getGender();
		List<Employee> employees;
		if (gender == null || "-1".equals(gender)) {
			employees = employeeRepository.findAll();
		} else {
			employees = employeeRepository.findByGender(gender.toUpperCase());
		}
		return employees.stream()
				.map(this::convertToStaffDTO)
				.collect(Collectors.toList());
	}
	
	private StaffDTO convertToStaffDTO(Employee employee) {
		return modelMapper.map(employee, StaffDTO.class);
	}
	
}
