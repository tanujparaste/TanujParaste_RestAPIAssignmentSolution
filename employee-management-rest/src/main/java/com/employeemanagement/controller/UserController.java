package com.employeemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.exception.MissingDetailsException;
import com.employeemanagement.model.User;
import com.employeemanagement.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final IUserService userService;

	public UserController(IUserService service) {
		this.userService = service;
	}

	@GetMapping("/list")
	public List<User> fetchAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User addNewUser(@RequestBody User user) {
		verifyIfAllDetailsAvailable(user);
		return userService.addUser(user);
	}

	private void verifyIfAllDetailsAvailable(User user) {
		if (ObjectUtils.isEmpty(user.getUsername()) || ObjectUtils.isEmpty(user.getPassword())
				|| ObjectUtils.isEmpty(user.getRoles())) {
			throw new MissingDetailsException("Missing details while inserting record: check the data being inserted");
		}
	}
}
