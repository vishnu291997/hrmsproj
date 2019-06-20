 package com.hrms.backend.dao;

import java.util.List;

import com.hrms.backend.modal.Leaves;
import com.hrms.backend.modal.Timesheet;
import com.hrms.backend.modal.User;

public interface TimesheetDao {
	
	void addTimesheet(Timesheet timesheet);
	//User getUser(String email);
	//void updateTimesheet(Timesheet timesheet);
	/*Timesheet getTimesheet(String email);*/
	//Timesheet getUser(String email);
	
	public Timesheet getTimesheetById(int id);
	

}
