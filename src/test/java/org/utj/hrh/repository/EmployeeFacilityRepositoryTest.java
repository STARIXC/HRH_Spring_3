package org.utj.hrh.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.utj.hrh.model.Employee;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeeFacilityRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    void findByEmployee() {
        Employee employee= new Employee();
        employee.setEmpNo("0004");


    }

    @Test
    void findByFacility() {
    }
}