package org.utj.hrh.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.utj.hrh.model.Employee;

import java.util.List;

@SpringBootTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    List<Employee> findAll(){
        List<Employee> employees= employeeRepository.findAll();
      return employees;
    }
}