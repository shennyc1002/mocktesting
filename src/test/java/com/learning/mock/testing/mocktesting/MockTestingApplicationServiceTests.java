package com.learning.mock.testing.mocktesting;

import com.learning.mock.testing.mocktesting.entity.Employee;
import com.learning.mock.testing.mocktesting.repository.EmployeeRepository;
import com.learning.mock.testing.mocktesting.service.EmployeeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MockTestingApplicationServiceTests {

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void saveEmployeeTest()
    {
        Employee expected = new Employee();
        expected.setEmployeeId(1);
        expected.setEmployeeFirstName("John");
        expected.setEmployeeLastName("Honai");
        expected.setEmployeeAge(25);
        expected.setEmployeeSalary(5000);

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeFirstName("John");
        employee.setEmployeeLastName("Honai");
        employee.setEmployeeAge(25);
        employee.setEmployeeSalary(5000);

        when(employeeRepository.save(employee)).thenReturn(expected);
        System.out.println("employeeService is "+employeeService);
        Employee actual = employeeService.saveEmployee(employee);
        System.out.println("Actual is "+actual);
        assertEquals(expected.getEmployeeFirstName(),actual.getEmployeeFirstName());
        verify(employeeRepository, Mockito.times(1)).save(employee);
    }

}
