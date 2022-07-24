package com.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employeemanagement.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("select r from Role r where r.name = ?1")
	Role findByName(String name);

	@Query("select r from Role r where r.id=?1 and r.name=?2")
	Role findByIdAndName(Long id, String name);
}
