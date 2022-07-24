package com.employeemanagement.exception;

public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 611514294243401072L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
