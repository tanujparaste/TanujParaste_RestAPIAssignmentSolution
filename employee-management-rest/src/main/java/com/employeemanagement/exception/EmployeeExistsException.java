package com.employeemanagement.exception;

import com.employeemanagement.model.Employee;

public class EmployeeExistsException extends RuntimeException {
	private static final long serialVersionUID = 6071104032463359349L;

	public EmployeeExistsException(Employee employee) {
		super("Employee already exists: [firstName:" + employee.getFirstName() + ",lastName:" + employee.getLastName()
				+ ",email:" + employee.getEmail() + "]");
	}
}
