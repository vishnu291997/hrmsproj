package com.hrms.backend.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.hrms.backend.dao.TimesheetDao;
import com.hrms.backend.modal.Leaves;
import com.hrms.backend.modal.Timesheet;
import com.hrms.backend.modal.User;

public class TimesheetDaoImpl implements TimesheetDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addTimesheet(Timesheet timesheet) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(timesheet);
		session.getTransaction().commit();
		session.close();
		
	}
	
/*
	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
				return null;
	}
*/
	
	/*
	public void updateTimesheet(Timesheet timesheet) {
		// TODO Auto-generated method stub
		
	}*/
	
	/*@Override
	public Timesheet getTimesheet(String email) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Timesheet timesheet = (Timesheet)session.get(Timesheet.class, email);
		return timesheet;
	}*/

	/*@Override
	Timesheet getUser(String email) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from User");
		List<User> user=query.list();
		return user;
		
	}*/
	
	/*@Override
	public List<Timesheet> getTimesheetByUser(String email) {
		
		Session session=sessionFactory.openSession();
		List<Timesheet> timesheets= null;
		session.beginTransaction();
		timesheets=session.createQuery("from User where email="+email).list();
		session.getTransaction().commit();
		session.close();
		return timesheets;
	
	}*/

	@Override
	public Timesheet getTimesheetById(int id) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Timesheet timesheet = (Timesheet)session.get(Timesheet.class,id);
		return timesheet;
	}

}
