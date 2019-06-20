package com.hrms.backend.daoimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.backend.dao.UserDao;
import com.hrms.backend.modal.User;


@Repository(value="uDao")
@Transactional
public class UserDaoImpl implements UserDao {

	public UserDaoImpl(){
		System.out.println("UserDaoImpl bean is created");
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void registration(User user) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public boolean isEmailUnique(String email) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from User where email=?");
		query.setString(0, email);
		User user=(User)query.uniqueResult();
		if(user!=null){//duplicate email address
			return false;
		}else{//unique email address
			return true;
		}
		
	}

	@Override
	public User login(User user) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from User where email=? and password=?");
		query.setString(0, user.getEmail());
		query.setString(1, user.getPassword());
		session.getTransaction().commit();
		return (User)query.uniqueResult();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public User getUser(String email) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		User user=(User)session.get(User.class, email);
		return user;
	}

}
