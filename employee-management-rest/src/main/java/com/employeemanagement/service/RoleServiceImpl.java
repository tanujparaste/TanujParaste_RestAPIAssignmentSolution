package com.employeemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employeemanagement.exception.RoleAlreadyExistsException;
import com.employeemanagement.model.Role;
import com.employeemanagement.repository.RoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {
	private final RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository repository) {
		this.roleRepository = repository;
	}

	@Override
	public Role addRole(Role role) {
		Role savedRole = roleRepository.findByName(role.getName().toUpperCase());
		if (savedRole == null) {
			role.setName(role.getName().toUpperCase());
			return roleRepository.save(role);
		}
		throw new RoleAlreadyExistsException("Cannot create new role, already exists: " + savedRole);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}
}
