package com.employeemanagement.service;

import java.util.List;

import com.employeemanagement.model.User;

public interface IUserService {

	User addUser(User user);

	List<User> getAllUsers();

}