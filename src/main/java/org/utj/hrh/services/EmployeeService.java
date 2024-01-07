package org.utj.hrh.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.*;
import org.utj.hrh.mapper.EmployeeDetailsMapper;
import org.utj.hrh.model.*;
import org.utj.hrh.repository.*;

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
	private final EducationLevelService educationLevelService;
	private final EmployeeStatusService employeeStatusService;
	private final DesignationService designationService;
	private final CarderCategoryService carderCategoryService;
	private final CountyService countyService;
	private final AddressRepository addressRepository;
	private final PersonRepository personRepository;
	private final PositionChangeRepository positionChangeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeFacilityRepository employeeFacilityRepository, ModelMapper modelMapper, EmployeeDetailsMapper employeeDetailsMapper, SupervisorService supervisorService, EducationLevelService educationLevelService, EmployeeStatusService employeeStatusService, DesignationService designationService, CarderCategoryService carderCategoryService, CountyService countyService, AddressRepository addressRepository, PersonRepository personRepository, PositionChangeRepository positionChangeRepository) {
		this.employeeRepository = employeeRepository;
		this.employeeFacilityRepository = employeeFacilityRepository;
		this.modelMapper = modelMapper;
		this.employeeDetailsMapper = employeeDetailsMapper;
		this.supervisorService = supervisorService;
		this.educationLevelService = educationLevelService;
		this.employeeStatusService = employeeStatusService;
		this.designationService = designationService;
		this.carderCategoryService = carderCategoryService;
		this.countyService = countyService;
		this.addressRepository = addressRepository;
		this.personRepository = personRepository;
		
		this.positionChangeRepository = positionChangeRepository;
	}
	
	public List<StaffDTO> getAll() {
		return employeeRepository.findAll()
				.stream().
				map(this::convertEntityDTO)
				.collect(Collectors.toList());
	}
	

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

	
	public void delete(Long id) throws EmployeeNotFoundException {
		Long countById = employeeRepository.countById(id);
		if (countById == null || countById == 0) {
			throw new EmployeeNotFoundException("could not find any Employee with ID :" + id);
		}
		employeeRepository.deleteById(id);
	}
	
	public Optional<EmployeeFacility> getEmployeeFacilityByEmpNo(Integer empNo) {
		return employeeFacilityRepository.findActiveFacilityByEmployeeId(empNo);
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
	

public EmployeeDetailsDTO getEmployeeDetails(Long id)  {
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
		List<EducationLevelDTO> educationLevelDTOS = educationLevelService.getAll() // Fetching logic
				.stream()
				.map(level -> convertToEducationLevelDTO(level)) // Conversion logic
				.collect(Collectors.toList());
		//		fetch education Levels
		List<DesignationDTO> designationDTOS = designationService.getAll() // Fetching logic
				.stream()
				.map(designation -> convertToDesignationDTO(designation)) // Conversion logic
				.collect(Collectors.toList());
		//		fetch Counties
		List<CountyDTO> countyDTOS = countyService.getActive() // Fetching logic
				.stream()
				.map(county -> convertToCountyDTO(county)) // Conversion logic
				.collect(Collectors.toList());	//		fetch Counties
		List<CarderCategoryDTO> carderCategoryDTOS = carderCategoryService.getAll() // Fetching logic
				.stream()
				.map(carderCategory -> convertToCarderCategoryDTO(carderCategory)) // Conversion logic
				.collect(Collectors.toList());
//		fetch emergency Records
	
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
	
	private EmployeeEmergencyContactDTO convertToEmployeeEmergencyContactDTO(EmployeeEmergencyContact employeeEmergencyContact) {
		return modelMapper.map(employeeEmergencyContact, EmployeeEmergencyContactDTO.class);
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
	public void updateContactInfo(Long employeeId, ContactInfoDTO contactInfoDTO) throws EntityNotFoundException {
		
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));
		// Save the address entities first
		Address homeAddress = convertToAddressEntity(contactInfoDTO.getHomeAddress(), employee);
		Address presentAddress = convertToAddressEntity(contactInfoDTO.getPresentAddress(), employee);
		addressRepository.save(homeAddress);
		addressRepository.save(presentAddress);
		// Update employee contact information
		employee.setPhone(contactInfoDTO.getPhone());
		employee.setEmail(contactInfoDTO.getEmail());
		employee.setAltPhone(contactInfoDTO.getAltPhone());
		employee.setAltEmail(contactInfoDTO.getAltEmail());
		employee.setPresentAddress(presentAddress);
		employee.setHomeAddress(homeAddress);
		
		employeeRepository.save(employee);
	}
	
	private Address convertToAddressEntity(AddressDTO addressDTO, Employee employee) throws EntityNotFoundException {
		Address address;
		if (addressDTO.getAddressId() != null) {
			// Existing address, fetch it from the database
			address = addressRepository.findById(addressDTO.getAddressId())
					.orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + addressDTO.getAddressId()));
		} else {
			// New address, create a new entity
			address = new Address();
		}
		
		// Set the address fields
		address.setStreet(addressDTO.getStreet());
		address.setPostalAddress(addressDTO.getPostalAddress());
		address.setCityTown(addressDTO.getCityTown());
		address.setPostalCode(addressDTO.getPostalCode());
		address.setCountry(addressDTO.getCountry());
		address.setEmployeeAddress(employee);
		
		return address;
	}
	
	public void updateBasicInfo(Long employeeId, BasicInfoDTO basicInfoDTO) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));
	
		Person person = personRepository.findByPersonNumber(basicInfoDTO.getPersonNumber());
		employee.setPerson(person);
		employee.setFirstName(basicInfoDTO.getFirstName());
		employee.setSurname(basicInfoDTO.getSurname());
		employee.setOtherName(basicInfoDTO.getOtherName());
		employee.setGender(basicInfoDTO.getGender());
		employee.setDob(basicInfoDTO.getDob());
		employee.setMaritalStatus(basicInfoDTO.getMaritalStatus());
		employee.setNationality(basicInfoDTO.getNationality());
		employee.setNationalId(basicInfoDTO.getNationalId());
		employee.setDisability(basicInfoDTO.getDisability());
		employee.setDisabilityExplain(basicInfoDTO.getDisabilityExplain());
		
		
		employeeRepository.save(employee);
	}
	
	public void updateStatutoryInfo(Long employeeId, StatutoryDetailsDTO statutoryDetailsDTO) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));

	employee.setKraPin(statutoryDetailsDTO.getKraPin());
	employee.setNssfNo(statutoryDetailsDTO.getNssfNo());
	employee.setNhifNo(statutoryDetailsDTO.getNhifNo());
	employee.setCertGoodConductNo(statutoryDetailsDTO.getCertGoodConductNo());
	employee.setHelbClearanceNo(statutoryDetailsDTO.getHelbClearanceNo());
	employee.setHelbBeneficiary(statutoryDetailsDTO.getHelbBeneficiary());
	
	employeeRepository.save(employee);
	}



}
