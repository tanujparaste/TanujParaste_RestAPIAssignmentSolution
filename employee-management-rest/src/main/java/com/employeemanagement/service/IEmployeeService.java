package com.employeemanagement.service;

import java.util.List;

import com.employeemanagement.model.Employee;

public interface IEmployeeService {

	List<Employee> getAllEmployees();

	Employee addEmployee(Employee employee);

	Employee getEmployeesById(Long id);

	List<Employee> getEmployeesByFirstName(String firstName);

	Employee updateEmployee(Employee employee);

	List<Employee> getAllEmployeesSortedByFirstName(String order);

	String deleteEmployeeById(Long id);

}