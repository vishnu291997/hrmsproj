package com.hrms.backend.dao;

import java.util.List;

import com.hrms.backend.modal.Event;



public interface EventDao {

	
public boolean addEvent(Event event);
	
	public boolean deleteEvent(int id);
	
	public Event getEvent(int id);
	
	public List<Event> listEvent();
}
