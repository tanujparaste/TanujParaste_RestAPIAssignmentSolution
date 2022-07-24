package com.employeemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employeemanagement.model.User;
import com.employeemanagement.repository.UserRepository;
import com.employeemanagement.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByName(username.toLowerCase());
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user: " + username);
		}
		return new MyUserDetails(user);
	}

}
