package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.*;
import org.utj.hrh.repository.EmployeeFacilityRepository;
import org.utj.hrh.repository.EmployeeRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeFacilityRepository employeeFacilityRepository;
    @Autowired
    private SupervisorService supervisorService;
    public List<Employee> getAll(){
        return  employeeRepository.findAll();
    }
    public void save(Employee employee) {
        boolean isUpdatingEmployee = (employee.getId() !=null);

        employeeRepository.save(employee);
    }

    public Employee getEmployee(String  id) throws EmployeeNotFoundException {
        try{
            return employeeRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new EmployeeNotFoundException("could not find any Employee with ID :"+id);
        }

    }
    public void delete(String id) throws  EmployeeNotFoundException {
        Long countById= employeeRepository.countById(id);
        if (countById==null || countById==0){
            throw new EmployeeNotFoundException("could not find any Employee with ID :"+id);
        }
        employeeRepository.deleteById(id);
    }

    public Optional<Supervisor> getActiveSupervisorForEmployee(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee != null) {
            // Find the employee facility
            EmployeeFacility employeeFacility = employeeFacilityRepository.findByEmployee(employee);


            if (employeeFacility != null) {
                // Find the health facility
                Facility healthFacility = employeeFacility.getFacility();
                System.out.println("Facility MFL: "+healthFacility);
                if (healthFacility != null) {
                    // Find the active supervisor
                    System.out.println("Facility MFL: "+healthFacility);
                    Optional<Supervisor> supervisor= supervisorService.getActiveSupervisorByFacility(healthFacility);
                    return supervisor;
                }
            }
//            Facility facility = employee.getEmployeeFacility().getFacility();
//            System.out.println(facility);
//            // Assuming you have a method in HealthFacilityRepository to get the active supervisor
//            return facility.getActiveSupervisor();
        }

        return null;
    }
}
