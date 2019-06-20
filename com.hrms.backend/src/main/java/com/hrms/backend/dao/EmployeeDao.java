package com.hrms.backend.dao;

import java.util.List;

import com.hrms.backend.modal.Employee;

public interface EmployeeDao {

	public List<Employee> getListOfEmployee();

	public void addEmployee(Employee e);

	public void updateEmployee(Employee e);

public void deleteEmployee(String empNo);

}
