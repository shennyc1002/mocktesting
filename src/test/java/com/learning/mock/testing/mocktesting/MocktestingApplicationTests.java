package com.learning.mock.testing.mocktesting;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.mock.testing.mocktesting.entity.Employee;
import com.learning.mock.testing.mocktesting.repository.EmployeeRepository;
import com.learning.mock.testing.mocktesting.service.EmployeeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MocktestingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeServiceImpl employeeService;

	private ObjectMapper mapper = new ObjectMapper();


	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAll() throws Exception
	{
		Employee expected = new Employee();
		expected.setEmployeeLastName("Honai");
		expected.setEmployeeFirstName("John");
		expected.setEmployeeAge(26);
		expected.setEmployeeSalary(5000);
		//employeeService.saveEmployee(expected);

		String response = mockMvc.perform(get("/employee/get"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		System.out.println("response "+response);
		List<Employee> result = mapper.readValue(response, new TypeReference<List<Employee>>(){});

		Assert.assertEquals("Get Should return one item",6,result.size());


	}

}

