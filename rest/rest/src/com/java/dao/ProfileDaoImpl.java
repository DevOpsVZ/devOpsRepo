package com.java.dao;

import java.util.Iterator;
import java.util.List;

import com.java.model.Profile;
import javax.transaction.Transactional; 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;

import java.util.List;
import java.io.Serializable;


public class ProfileDaoImpl implements ProfileDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Profile> getList(String where){
		Session session = sessionFactory.openSession();
		
		List<Profile> profileList = session.createQuery("from profiles " + where).list();
		session.close();
		return profileList;
	}
	
	public int createProfile(Profile profile){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.saveOrUpdate(profile);
		tx.commit();
		Serializable id = session.getIdentifier(profile);
		session.close();
		return (Integer)id;
	}
	
	public int deleteProfile(int id){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Profile profile = (Profile)session.load(Profile.class, id);
		session.delete(profile);
		tx.commit();
		Serializable sid = session.getIdentifier(profile);
		session.close();
		return (Integer)sid;
	}
	
	public int updateProfile(int id, String fullName, String skills, String mobile){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Profile profile = (Profile)session.load(Profile.class, id);
		profile.setFullName(fullName);
		profile.setSkills(skills);
		profile.setMobile(mobile);
		session.saveOrUpdate(profile);
		tx.commit();
		Serializable sid = session.getIdentifier(profile);
		session.close();
		return (Integer)sid;
	}
}