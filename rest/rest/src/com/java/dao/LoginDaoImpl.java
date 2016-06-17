package com.java.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.model.User;

public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public User validateUser(String username, String password){
		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User user where user.username=? and user.password=?");
		query.setString(0, username);
		query.setString(1, password);
		List<User> list = query.list();
		Iterator<User> it = list.iterator();
		User user = null;
		if(it.hasNext())
		{
			user = (User) it.next();
		}
		
		return user;
	}
}