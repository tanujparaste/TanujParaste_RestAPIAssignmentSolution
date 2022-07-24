package com.employeemanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employeemanagement.exception.RoleAlreadyExistsException;
import com.employeemanagement.exception.RoleNotFoundException;
import com.employeemanagement.exception.UserAlreadyExistsException;
import com.employeemanagement.model.Role;
import com.employeemanagement.model.User;
import com.employeemanagement.repository.RoleRepository;
import com.employeemanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
	}

	@Override
	public User addUser(User user) {
		User savedUser = userRepository.findUserByName(user.getUsername());
		if (savedUser != null) {
			verifyRoleData(user);
			if (user.getPassword().equals(savedUser.getPassword())) {
				List<Role> savedUserRoles = savedUser.getRoles();
				Boolean roleExists = hasUserRole(user, savedUserRoles);
				if (!roleExists) {
					savedUserRoles.addAll(user.getRoles());
					savedUser.setRoles(savedUserRoles);
					savedUser.setPassword(encoder.encode(savedUser.getPassword()));
					return userRepository.save(savedUser);
				}
				throw new RoleAlreadyExistsException("Cannot assign new role, already exists: " + savedUser.getRoles());
			} else if (!user.getPassword().equals(savedUser.getPassword())) {
				throw new UserAlreadyExistsException("User already exists: " + user.getUsername());
			}
		}
		verifyRoleData(user);
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);

	}

	private void verifyRoleData(User user) {
		// verify if all role information is correct
		for (Role role : user.getRoles()) {
			if (!isRoleIdAndNameComboCorrect(role.getId(), role.getName())) {
				throw new RoleNotFoundException("Either Role does not exist or incorrect data: " + role);
			}
		}
	}

	private boolean isRoleIdAndNameComboCorrect(long id, String name) {
		if (roleRepository.findByIdAndName(id, name.toUpperCase()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	private boolean hasUserRole(User user, List<Role> roles) {
		Map<Long, String> savedRoles = new HashMap<>();
		for (Role role : roles) {
			savedRoles.put(role.getId(), role.getName());
		}
		for (Role role : user.getRoles()) {
			if (savedRoles.get(role.getId()) != null && savedRoles.get(role.getId()).equalsIgnoreCase(role.getName())) {
				return true;
			}
		}
		return false;
	}
}
