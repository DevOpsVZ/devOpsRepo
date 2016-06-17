package com.java.dao;

import javax.transaction.Transactional; 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;

import java.util.List;
import com.java.model.Job;
import java.io.Serializable;

public class JobDaoImpl implements JobDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Job> getList(String where){
		Session session = sessionFactory.openSession();
		//List<Job> jobList = session.createQuery("select job.id, job.jobTitle,job.description, user.username from Job job , User user " + where).list();
		List<Job> jobList = session.createQuery("from Job " + where).list();
		session.close();
		return jobList;
	}
	
	@Transactional
	public int createJob(Job job){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.saveOrUpdate(job);
		tx.commit();
		Serializable id = session.getIdentifier(job);
		session.close();
		return (Integer)id;
	}
	
	public int deleteJob(int id){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Job job = (Job)session.load(Job.class, id);
		session.delete(job);
		tx.commit();
		Serializable sid = session.getIdentifier(job);
		session.close();
		return (Integer)sid;
	}
	public int updateJob(int id, String title, String description, int status){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Job job = (Job)session.load(Job.class, id);
		job.setJobTitle(title);
		job.setDescription(description);
		job.setStatus(status);
		session.saveOrUpdate(job);
		tx.commit();
		Serializable sid = session.getIdentifier(job);
		session.close();
		return (Integer)sid;
	}
}