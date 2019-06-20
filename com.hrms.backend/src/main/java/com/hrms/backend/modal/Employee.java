package com.hrms.backend.modal;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue
	private int empNo;
	public int getEmpNo() {
	return empNo;
}
public void setEmpNo(int empNo) {
	this.empNo = empNo;
}

	private String firstName;
	private String lastName;
	private String deptName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", firstName=" + firstName + ", lastName=" + lastName + ", deptName="
				+ deptName + "]";
}
	
}
