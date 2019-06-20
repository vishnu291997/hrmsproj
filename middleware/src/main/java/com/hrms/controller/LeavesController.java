package com.hrms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.backend.dao.LeavesDao;
import com.hrms.backend.dao.NotificationDao;
import com.hrms.backend.dao.UserDao;
import com.hrms.backend.modal.ErrorClazz;
import com.hrms.backend.modal.Leaves;
import com.hrms.backend.modal.Notification;
import com.hrms.backend.modal.User;



@RestController
public class LeavesController {
	@Autowired
	LeavesDao leavesDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NotificationDao notificationDao;
	
	@RequestMapping(value="/addleaves",method=RequestMethod.POST)
		public ResponseEntity<?> addLeaves(@RequestBody Leaves leaves,HttpSession session){
			System.out.println(leaves.getLeaveSubject());
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
		leaves.setPostedOn(new Date());
			
			leaves.setPostedBy(userDao.getUser(email));
			try{
			leavesDao.addLeaves(leaves);
			}catch(Exception e){
				ErrorClazz errorClazz=new ErrorClazz(6,"Unable to insert blogpost details.."+e.getMessage());
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Leaves>(leaves,HttpStatus.OK);
		}
	
	@RequestMapping(value="/getleaves/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getLeave(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		Leaves leave=leavesDao.getLeaves(id);
		return new ResponseEntity<Leaves>(leave,HttpStatus.OK);
	}
			
	@RequestMapping(value="/approvedleaves",method=RequestMethod.GET)
	public ResponseEntity<?> getApprovedLeaves(HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<Leaves> approvedLeaves=leavesDao.getApprovedLeaves();
		return new ResponseEntity<List<Leaves>>(approvedLeaves,HttpStatus.OK);
	}		
	
	@RequestMapping(value="/leaveswaitingforapproval",method=RequestMethod.GET)
	public ResponseEntity<?> getLeavesWaitingForApproval(HttpSession session){
//		//AUTHENTICATION
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
		List<Leaves> leavesWaitingForApproval=leavesDao.getLeavesWaitingForApproval();
		return new ResponseEntity<List<Leaves>>(leavesWaitingForApproval,HttpStatus.OK);
	}

	@RequestMapping(value="/approveleaves",method=RequestMethod.PUT)
	public ResponseEntity<?> approveBlogPost(@RequestBody Leaves leaves,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			ErrorClazz errorClazz=new ErrorClazz(6,"Access Denied..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		//how to update approvalstatus
		leaves.setApprovalStatus(true);
		leavesDao.updateLeaves(leaves);
		
		Notification notification=new Notification();
		notification.setApprovalStatus("Approved");
		notification.setLeaveSubject(leaves.getLeaveSubject());
		notification.setEmail(leaves.getPostedBy().getEmail());
        notificationDao.addNotification(notification);
		
		
		return new ResponseEntity<Leaves>(leaves,HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectleaves",method=RequestMethod.PUT)
	public ResponseEntity<?> rejectLeaves(@RequestBody Leaves leaves,@RequestParam String rejectionReason,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			ErrorClazz errorClazz=new ErrorClazz(6,"Access Denied..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		Notification notification=new Notification();
		notification.setApprovalStatus("Rejected");
		notification.setLeaveSubject(leaves.getLeaveSubject());
		notification.setEmail(leaves.getPostedBy().getEmail());
		notification.setRejectionReason(rejectionReason);
		notificationDao.addNotification(notification);
		
		leavesDao.deleteLeaves(leaves);
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}



	
}
