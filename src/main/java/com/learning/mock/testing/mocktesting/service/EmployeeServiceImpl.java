package com.learning.mock.testing.mocktesting.service;

import com.learning.mock.testing.mocktesting.entity.Employee;
import com.learning.mock.testing.mocktesting.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }


    public Employee getEmployeeById(int employeeId)
    {
        return employeeRepository.findById(employeeId).get();
    }

    public Employee saveEmployee(Employee employee)
    {
        return  employeeRepository.save(employee);
    }

    public Iterable<Employee> getEmployees()
    {
        System.out.println("Inside GetEmployee Service");
        return employeeRepository.findAll();
    }
}
