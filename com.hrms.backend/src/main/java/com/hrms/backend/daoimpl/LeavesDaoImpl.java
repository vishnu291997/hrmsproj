package com.hrms.backend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hrms.backend.dao.LeavesDao;
import com.hrms.backend.modal.Leaves;

@Repository
@Transactional
public class LeavesDaoImpl implements LeavesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addLeaves(Leaves leaves) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(leaves);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public List<Leaves> getApprovedLeaves() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Leaves where approvalStatus=true");
		List<Leaves> leaves=query.list();
		return leaves;
	}

	@Override
	public List<Leaves> getLeavesWaitingForApproval() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("from Leaves where approvalStatus=false");
		List<Leaves> leaves=query.list();
		return leaves;
	}

	@Override
	public Leaves getLeaves(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Leaves leaves=(Leaves)session.get(Leaves.class, id);
		return leaves;
	}

	@Override
	public void updateLeaves(Leaves leaves) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(leaves);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteLeaves(Leaves leaves) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(leaves);
		session.getTransaction().commit();
		session.close();
		
	}

}
