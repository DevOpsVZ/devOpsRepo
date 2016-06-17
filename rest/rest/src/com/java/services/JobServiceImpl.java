package com.java.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.java.model.Job;
import com.java.dao.JobDao;

public class JobServiceImpl implements JobService{
	
	@Autowired
	JobDao jobDao;
	
	public List<Job> getList(String where){
		return jobDao.getList(where);
	}
	
	public int createJob(Job job){
		return jobDao.createJob(job);
	}
	
	public int deleteJob(int id){
		return jobDao.deleteJob(id);
	}
	
	public int updateJob(int id, String jobTitle, String description, int status){
		return jobDao.updateJob(id, jobTitle, description, status);
	}
}