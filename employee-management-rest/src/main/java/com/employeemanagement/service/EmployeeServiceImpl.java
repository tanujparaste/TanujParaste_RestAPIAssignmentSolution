package com.employeemanagement.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.employeemanagement.exception.EmployeeExistsException;
import com.employeemanagement.exception.EmployeeIdNotFoundException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.util.EmployeeComparator;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.employeeRepository = repository;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		Employee savedEmployee = employeeRepository.findEmployee(employee.getFirstName(), employee.getLastName(),
				employee.getEmail());
		if (savedEmployee == null) {
			employee.setFirstName(employee.getFirstName().toLowerCase());
			employee.setLastName(employee.getLastName().toLowerCase());
			employee.setEmail(employee.getEmail().toLowerCase());
			return employeeRepository.save(employee);
		}
		throw new EmployeeExistsException(savedEmployee);
	}

	@Override
	public Employee getEmployeesById(Long id) {
		try {
			return employeeRepository.findById(id).get();
		} catch (Exception ex) {
			throw new EmployeeIdNotFoundException(id);
		}
	}

	@Override
	public List<Employee> getEmployeesByFirstName(String firstName) {
		return employeeRepository.findEmployeeByFirstName(firstName);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		try {
			Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
			savedEmployee.setFirstName(employee.getFirstName());
			savedEmployee.setLastName(employee.getLastName());
			savedEmployee.setEmail(employee.getEmail());
			employeeRepository.save(savedEmployee);
			return savedEmployee;
		} catch (Exception ex) {
			throw new EmployeeIdNotFoundException(employee.getId());
		}
	}

	@Override
	public List<Employee> getAllEmployeesSortedByFirstName(String order) {
		List<Employee> employees = getAllEmployees();
		Collections.sort(employees, new EmployeeComparator(order));
		return employees;
	}

	@Override
	public String deleteEmployeeById(Long id) {
		try {
			employeeRepository.deleteById(id);
			return "Deleted employee id - " + id;
		} catch (Exception ex) {
			throw new EmployeeIdNotFoundException(id);
		}
	}
}
