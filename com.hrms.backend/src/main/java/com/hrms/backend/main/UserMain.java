package com.hrms.backend.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hrms.backend.config.SpringConfig;
import com.hrms.backend.dao.UserDao;
import com.hrms.backend.modal.User;

public class UserMain {
	public static void main(String args[]) {
		ApplicationContext c=new AnnotationConfigApplicationContext(SpringConfig.class);
		
		UserDao udao = (UserDao)c.getBean("uDao");
		User u = new User();
		u.setEmail("vanish@gmail.com");
		u.setPassword("vishnu");
		u.setPhonenumber("9538812961");
		u.setRole("ADMIN");
		u.setOnline(false);
		udao.registration(u);
		System.out.println("saved");
		
	}
	
}
