package com.employeemanagement.exception;

public class MissingDetailsException extends RuntimeException {
	private static final long serialVersionUID = -8660960531231417384L;

	public MissingDetailsException(String message) {
		super(message);
	}
}