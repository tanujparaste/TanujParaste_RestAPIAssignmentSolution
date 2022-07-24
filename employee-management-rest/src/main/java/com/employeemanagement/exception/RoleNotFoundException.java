package com.employeemanagement.exception;

public class RoleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -8728217432478452834L;

	public RoleNotFoundException(String message) {
		super(message);
	}
}
