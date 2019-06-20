package com.hrms.backend.dao;

import com.hrms.backend.modal.User;

public interface UserDao {
	void registration(User user);
	boolean isEmailUnique(String email);
	User login(User user);
	void updateUser(User user);
	User getUser(String email);
}
