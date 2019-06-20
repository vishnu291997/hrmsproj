package com.hrms.backend.modal;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="timesheet_acs")
public class Timesheet {
	@Id
	@GeneratedValue
	private int timesheetid;
	 
	@ManyToOne
	private User email;
	
	private User emp_name;
	private String Location;
	private String project;
	@Lob
	private String tasks;
	private Date date;
	/*private Timestamp Start_time;
	private Timestamp End_time;*/
	private int Duration;
	
	public int getTimesheetid() {
		return timesheetid;
	}
	public void setTimesheetid(int timesheetid) {
		this.timesheetid = timesheetid;
	}
	
	public User getEmail() {
		return email;
	}
	public void setEmail(User email) {
		this.email = email;
	}
	
	public User getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(User emp_name) {
		this.emp_name = emp_name;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getTasks() {
		return tasks;
	}
	public void setTasks(String tasks) {
		this.tasks = tasks;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	/*public Timestamp getStart_time() {
		return Start_time;
	}
	public void setStart_time(Timestamp start_time) {
		Start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return End_time;
	}
	public void setEnd_time(Timestamp end_time) {
		End_time = end_time;
	}*/
	public int getDuration() {
		return Duration;
	}
	public void setDuration(int duration) {
		Duration = duration;
	}
}
