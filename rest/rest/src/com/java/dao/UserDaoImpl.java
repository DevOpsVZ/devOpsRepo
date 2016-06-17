package com.java.dao;

import javax.transaction.Transactional; 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;

import java.util.List;
import java.io.Serializable;
import com.java.model.User;

public class UserDaoImpl implements UserDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<User> getList(String where){
		Session session = sessionFactory.openSession();
		List<User> userList = session.createQuery("from User " + where).list();
		session.close();
		return userList;
	}
	
	@Transactional
	public int addUser(User user){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.saveOrUpdate(user);
		tx.commit();
		Serializable id = session.getIdentifier(user);
		session.close();
		return (Integer)id;
	}
	
	public int deleteUser(int id){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User)session.load(User.class, id);
		session.delete(user);
		tx.commit();
		Serializable sid = session.getIdentifier(user);
		session.close();
		return (Integer)sid;
	}
	
	public int updateUser(int id, String password){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User)session.load(User.class, id);
		user.setPassword(password);
		session.saveOrUpdate(user);
		tx.commit();
		Serializable sid = session.getIdentifier(user);
		session.close();
		return (Integer)sid;
	}
}