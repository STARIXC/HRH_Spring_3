package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Designation;
import org.utj.hrh.model.Employee;
import org.utj.hrh.repository.DesignationRepository;
import org.utj.hrh.repository.EmployeeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll(){
        return (List<Employee>) employeeRepository.findAll();
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
        Long countById= employeeRepository.countByEmpNo(id);
        if (countById==null || countById==0){
            throw new EmployeeNotFoundException("could not find any Employee with ID :"+id);
        }
        employeeRepository.deleteById(id);
    }

}
