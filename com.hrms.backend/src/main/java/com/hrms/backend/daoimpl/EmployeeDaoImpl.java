package com.hrms.backend.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.backend.dao.EmployeeDao;
import com.hrms.backend.modal.Employee;
import com.hrms.backend.util.BackendUtil;

@Repository(value="eDao")
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	 public List<Employee> getListOfEmployee()
	 {
	Session session=sessionfactory.openSession();
		session.beginTransaction();
		List<Employee>getListOfEmployee=session.createQuery("from Employee").getResultList();
		return getListOfEmployee;
	}

	@Override
	public void addEmployee(Employee e) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void updateEmployee(Employee e) {
		
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.update(e);
		session.getTransaction().commit();
		session.close();
	}

    @Override
	public void deleteEmployee(String empNo) {
		Session session=sessionfactory.openSession();
		session.beginTransaction();
		session.delete(empNo);
		session.getTransaction().commit();
		session.close();
}
}