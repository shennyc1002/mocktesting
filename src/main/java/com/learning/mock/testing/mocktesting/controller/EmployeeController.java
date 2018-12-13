package com.learning.mock.testing.mocktesting.controller;


import com.learning.mock.testing.mocktesting.entity.Employee;
import com.learning.mock.testing.mocktesting.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService)
    {
        this.employeeService = employeeService;
    }
    @GetMapping(value="/get/{employeeid}")
    public Employee getEmployeeById(@PathVariable("employeeid") int employeeid)
    {
        return employeeService.getEmployeeById(employeeid);
    }

    @RequestMapping(value="/get", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Employee>> getAll()
    {
        System.out.println("inside get");
        Iterable<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<Iterable<Employee>>(employees, HttpStatus.OK);
    }

    @PostMapping(value="/save")
    public Employee saveEmployee(@RequestBody Employee employee)
    {
        return employeeService.saveEmployee(employee);
    }

}
