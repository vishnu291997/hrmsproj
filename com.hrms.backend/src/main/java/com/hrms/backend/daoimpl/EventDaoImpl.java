package com.hrms.backend.daoimpl;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.backend.dao.EventDao;
import com.hrms.backend.modal.Event;




@Repository
@Transactional
public class EventDaoImpl implements EventDao {
private static final Logger log = LoggerFactory.getLogger(EventDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	public EventDaoImpl(SessionFactory sessionFactory) 
	{
		try 
		{
			this.sessionFactory = sessionFactory;
			log.info("Connection Established Successfully");
		} 
		catch (Exception ex) 
		{
			log.error("Failed to establish connection");
			ex.printStackTrace();
		}
	}
	
	
	@Override
	public boolean addEvent(Event event) {
		try
		{
			Session session = sessionFactory.openSession();
			//sessionFactory.openSession();
					session.beginTransaction();
			session.save(event);
			session.getTransaction().commit();
			log.info("Event has been saved");
			return true;
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.error("Error saving Event");
			return false;
		}
	}

	@SuppressWarnings({ "rawtypes" })
	

	@Override
	public boolean deleteEvent(int id) {
		log.info("Entering Delete Event");
		try 
		{
			Session session = sessionFactory.openSession();
			
			String sql = "FROM Event where id = '"+id+"'";
			Query query = session.createQuery(sql);
			Event event = (Event) query.uniqueResult();
			if(event == null)
			{
				log.warn("Event Not Found");
				return false;
			}
			
			session.delete(event);
			log.info("Success delete Event");
			session.getTransaction().commit();
			return true;
		}
		catch (HibernateException e) 
		{
			log.error("Error Deleting Event");
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Event getEvent(int id) {

		try
		{
			Session session = sessionFactory.openSession();
			String sql = "FROM Event where id = '"+id+"'";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(sql);
			Event event = (Event) query.uniqueResult();
			session.getTransaction().commit();
			log.info("Event has been retrieved");
			
			return event;
		}
		catch(HibernateException ex)
		{
			log.error("Event not retrieved");
			ex.printStackTrace();
			return null;	
		}
	}

	@Override
	public List<Event> listEvent() {
		try
		{
			Session session = sessionFactory.openSession();
			String sql = "FROM Event";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(sql);
			@SuppressWarnings("unchecked")
			List<Event> events = query.list();
			log.info("Event list has been retrieved");
			//session.getTransaction().commit();
			return events;
		}
		catch(HibernateException ex)
		{
			log.error("Event list has some error");
			ex.printStackTrace();
			return null;	
		}
	}
	

}
