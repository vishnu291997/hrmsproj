package com.hrms.backend.daoimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.backend.dao.ProfilePictureDao;
import com.hrms.backend.modal.ProfilePicture;
@Repository
@Transactional

public class ProfilePictureDaoImpl implements ProfilePictureDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void uploadProfilePicture(ProfilePicture profilePicture) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(profilePicture);
		session.getTransaction().commit();
	}

	@Override
	public ProfilePicture getProfilePicture(String email) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, email);
		return profilePicture;
	}

	
	
	
	
	
}
