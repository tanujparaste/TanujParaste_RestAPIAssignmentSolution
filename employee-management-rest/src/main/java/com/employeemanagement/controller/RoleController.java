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
import com.employeemanagement.model.Role;
import com.employeemanagement.service.IRoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	private final IRoleService roleService;

	public RoleController(IRoleService service) {
		this.roleService = service;
	}

	@GetMapping("/list")
	public List<Role> fetchAllRoles() {
		return roleService.getAllRoles();
	}

	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Role addNewRole(@RequestBody Role role) {
		if (ObjectUtils.isEmpty(role.getName())) {
			throw new MissingDetailsException("Missing details while inserting record: check the data being inserted");
		}
		return roleService.addRole(role);
	}
}
