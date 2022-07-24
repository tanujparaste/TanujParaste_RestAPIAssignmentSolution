package com.employeemanagement.util;

import java.util.Comparator;

import com.employeemanagement.model.Employee;

public class EmployeeComparator implements Comparator<Employee> {
	private final String order;

	public EmployeeComparator(String order) {
		this.order = order;
	}

	@Override
	public int compare(Employee emp, Employee otherEmp) {
		if (order.equalsIgnoreCase("desc"))
			return otherEmp.getFirstName().compareToIgnoreCase(emp.getFirstName());
		else
			return emp.getFirstName().compareToIgnoreCase(otherEmp.getFirstName());
	}

}
