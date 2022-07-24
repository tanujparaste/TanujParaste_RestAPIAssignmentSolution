package com.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.employeemanagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, QueryByExampleExecutor<Employee> {
	@Query("select e from Employee e where e.firstName=lower(?1) and e.lastName=lower(?2) and e.email=lower(?3)")
	Employee findEmployee(String firstName, String lastName, String email);

	@Query("select e from Employee e where e.firstName = ?1")
	List<Employee> findEmployeeByFirstName(String firstName);
}
