package com.employeemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.exception.MissingDetailsException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.service.IEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private final IEmployeeService employeeService;

	public EmployeeController(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	public List<Employee> fetchAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/sort")
	public List<Employee> fetchSortedEmployees(@RequestParam String order) {
		return employeeService.getAllEmployeesSortedByFirstName(order);
	}

	@GetMapping("/get/{id}")
	public Employee fetchEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeesById(id);
	}

	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee addNewEmployee(@RequestBody Employee employee) {
		verifyIfAllDetailsAvailable(employee);
		return employeeService.addEmployee(employee);
	}

	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
		verifyIfAllDetailsAvailable(employee);
		return employeeService.updateEmployee(employee);
	}

	@GetMapping("/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.getEmployeesByFirstName(firstName);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		return employeeService.deleteEmployeeById(id);
	}

	private void verifyIfAllDetailsAvailable(Employee employee) {
		if (ObjectUtils.isEmpty(employee.getFirstName()) || ObjectUtils.isEmpty(employee.getLastName())
				|| ObjectUtils.isEmpty(employee.getEmail())) {
			throw new MissingDetailsException("Missing details while inserting record: check the data being inserted");
		}
	}
}
