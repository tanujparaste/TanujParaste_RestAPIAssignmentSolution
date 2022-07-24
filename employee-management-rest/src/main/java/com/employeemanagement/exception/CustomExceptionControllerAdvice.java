package com.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionControllerAdvice {
	@ResponseBody
	@ExceptionHandler(RoleAlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.SEE_OTHER)
	public String roleAlreadyExistsExcepitonHandler(RoleAlreadyExistsException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(EmployeeIdNotFoundException.class)
	@ResponseStatus(code = HttpStatus.SEE_OTHER)
	public String employeeIdNotFoundExceptionHandler(EmployeeIdNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(EmployeeExistsException.class)
	@ResponseStatus(code = HttpStatus.SEE_OTHER)
	public String EmployeeExistsException(EmployeeExistsException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(RoleNotFoundException.class)
	@ResponseStatus(code = HttpStatus.SEE_OTHER)
	public String RoleNotFoundException(RoleNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.SEE_OTHER)
	public String UserAlreadyExistsException(UserAlreadyExistsException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(MissingDetailsException.class)
	@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
	public String MissingDetailsException(MissingDetailsException ex) {
		return ex.getMessage();
	}
}
