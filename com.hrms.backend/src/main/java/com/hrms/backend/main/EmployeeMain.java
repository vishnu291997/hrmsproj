package com.hrms.backend.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hrms.backend.config.SpringConfig;
import com.hrms.backend.dao.EmployeeDao;
import com.hrms.backend.modal.Employee;



public class EmployeeMain {

	public static void main(String args[]) {
		ApplicationContext c=new AnnotationConfigApplicationContext(SpringConfig.class);
		System.out.println("saved");
		EmployeeDao edao = (EmployeeDao)c.getBean("eDao");
		Employee e = new Employee();
		e.setFirstName("vishnu");
		e.setLastName("menon");
		e.setDeptName("T24");
		edao.addEmployee(e);
		
	}
	
}

