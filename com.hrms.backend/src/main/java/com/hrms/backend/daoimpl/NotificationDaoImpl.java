package com.hrms.backend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.backend.dao.NotificationDao;
import com.hrms.backend.modal.Notification;



@Repository("notificationDao")
@Transactional
public class NotificationDaoImpl implements NotificationDao {
	@Autowired
private SessionFactory sessionFactory;
	public void addNotification(Notification notification) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(notification);
		session.getTransaction().commit();
		session.close();
	}
	public List<Notification> getNotificationNotViewed(String email) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Notification where viewed=0 and email=?");
		query.setString(0, email);
		return query.list();
	}
	public Notification getNotification(int id) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Notification notification=(Notification)session.get(Notification.class, id);
		return notification;
	}
	public void updateNotification(int id) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Notification notification=(Notification)session.get(Notification.class, id);
		notification.setViewed(true);
		session.update(notification);//update notification_s190035 set viewed=1 where id=?
		session.getTransaction().commit();
		session.close();
	}

}
