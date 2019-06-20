package com.hrms.backend.util;

public class BackendUtil {
	public static final String EMP_ID_PATTERN = "EMP000";
	public static final String SELECT_EMP_QUERY = "select * from EMPLOYEE;";
	public static final String INSERT_EMP_QUERY = "insert into EMPLOYEE(EMP_NO, FIRST_NAME, LAST_NAME, DEPT_NAME) select max(EMP_NO)+1, ?, ?, ? from employee;";
	public static final String UPDATE_EMP_QUERY = "update EMPLOYEE set FIRST_NAME = ?, LAST_NAME = ?, DEPT_NAME = ? where EMP_NO = ?";
	public static final String DELETE_EMP_QUERY = "delete from EMPLOYEE WHERE EMP_NO = ?";
	public static final String SELECT_EMP_MAX_EMP_NO = "SELECT MAX(EMP_NO) FROM EMPLOYEE;";
	
	public static int getEmpId(String empId) {
		if(empId != null) {
			String temp = empId.substring(5);
			return new Integer(temp);
		}
		return 0;
}
}
