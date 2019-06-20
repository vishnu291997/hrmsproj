package com.hrms.backend.dao;

import java.util.List;

import com.hrms.backend.modal.Leaves;

public interface LeavesDao {
	void addLeaves(Leaves leaves);
	List<Leaves> getApprovedLeaves();
	List<Leaves> getLeavesWaitingForApproval();
	Leaves getLeaves(int id);
	void updateLeaves(Leaves leaves);
	void deleteLeaves(Leaves leaves);
}
