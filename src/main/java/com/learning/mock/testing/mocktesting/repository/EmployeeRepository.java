package com.learning.mock.testing.mocktesting.repository;

import com.learning.mock.testing.mocktesting.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
