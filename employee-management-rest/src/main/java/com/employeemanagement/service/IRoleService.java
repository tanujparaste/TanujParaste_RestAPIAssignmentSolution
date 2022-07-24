package com.employeemanagement.service;

import java.util.List;

import com.employeemanagement.model.Role;

public interface IRoleService {

	Role addRole(Role role);

	List<Role> getAllRoles();

}