package com.hrms.backend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hrms.backend.modal.Notification;



@Repository("notificationDao")
public interface NotificationDao {
	void addNotification(Notification notification);
	List<Notification> getNotificationNotViewed(String email);
	Notification  getNotification(int id);
	void  updateNotification(int id);

}
