/*package com.hrms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.backend.dao.TimesheetDao;
import com.hrms.backend.dao.UserDao;
import com.hrms.backend.modal.ErrorClazz;
import com.hrms.backend.modal.Leaves;
import com.hrms.backend.modal.Timesheet;
import com.hrms.backend.modal.User;

@RestController
public class TimesheetController {

	@Autowired
	UserDao userDao;
	
	
	@Autowired
	TimesheetDao timeSheetDao;
	
	@RequestMapping(value="/addtimesheet",method=RequestMethod.POST)
	public ResponseEntity<?> addtimesheet(@RequestBody Timesheet timesheet,HttpSession session){
		System.out.println(timesheet.getProject());
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
	//leaves.setPostedOn(new Date());
		
		timesheet.setEmp_name(userDao.getUser(email));
		try{

		timeSheetDao.addTimesheet(timesheet);;
		}catch(Exception e){
			ErrorClazz errorClazz=new ErrorClazz(6,"Unable to insert timesheet details.."+e.getMessage());
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Timesheet>(timesheet,HttpStatus.OK);
	}


	@RequestMapping(value="/listusers/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> listusers(HttpSession session){
	
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		//AUTHORIZATION - only admin can view list of blogs waiting for approval
		User user=userDao.getUser(email); 
		
		if(!user.getRole().equals("ADMIN")){
			ErrorClazz errorClazz=new ErrorClazz(6,"Access Denied..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<User> listusers=timeSheetDao.
		return new ResponseEntity(listusers,HttpStatus.OK);
	
		
	}
	
	@RequestMapping(value="/gettimesheet/{id}")
	public ResponseEntity<?> gettimesheet(HttpSession session){
		
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null) {
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")) {
			ErrorClazz errorClazz=new ErrorClazz(6,"Access Denied..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		Timesheet timesheet = timeSheetDao.getTimesheetById(id);
		return new ResponseEntity<Timesheet>(timesheet,HttpStatus.OK);
		
		}
		
	}
		
	
	



*/