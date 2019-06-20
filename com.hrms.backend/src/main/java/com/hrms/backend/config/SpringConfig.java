package com.hrms.backend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;


import com.hrms.backend.modal.Employee;
import com.hrms.backend.modal.Event;
import com.hrms.backend.modal.Leaves;
import com.hrms.backend.modal.Notification;
import com.hrms.backend.modal.ProfilePicture;
import com.hrms.backend.modal.User;

@Configuration
@ComponentScan("com.hrms")
public class SpringConfig {
	@Autowired
	@Bean(name= {"dataSource"})
	
	public DataSource getdataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/world?useSSL=true");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		return dataSource;
	}
	

	@Bean
	public SessionFactory sessionFactory()
	{
		LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(getdataSource());
		Properties p=new Properties();
		p.put("hibernate.show_sql","true");
	    p.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");

		p.put("hibernate.hbm2ddl.auto", "update");
		builder.addProperties(p);
		builder.addAnnotatedClass(Employee.class);
		builder.addAnnotatedClass(User.class);
		builder.addAnnotatedClass(Leaves.class);
		builder.addAnnotatedClass(Notification.class);
		builder.addAnnotatedClass(ProfilePicture.class);
		builder.addAnnotatedClass(Event.class);
		return builder.buildSessionFactory();
	}
}
