package com.hrms.backend.modal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="leaves_acs")

public class Leaves {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String leaveSubject;
	@Lob
	private String leaveContent;
	private Date postedOn;
	@ManyToOne
	private User postedBy;
	private boolean approvalStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLeaveSubject() {
		return leaveSubject;
	}
	public void setLeaveSubject(String leaveSubject) {
		this.leaveSubject = leaveSubject;
	}
	public String getLeaveContent() {
		return leaveContent;
	}
	public void setLeaveContent(String leaveContent) {
		this.leaveContent = leaveContent;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public User getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	public boolean isApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	
}
