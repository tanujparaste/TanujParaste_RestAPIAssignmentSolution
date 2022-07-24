package com.employeemanagement.exception;

public class EmployeeIdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 5554292993056811649L;

	public EmployeeIdNotFoundException(Long id) {
		super("Employee id not found: " + id);
	}
}
