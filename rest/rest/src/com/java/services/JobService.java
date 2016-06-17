package com.java.services;

import java.util.List;
import com.java.model.Job;

public interface JobService{
	public List<Job> getList(String where);
	public int createJob(Job job);
	public int deleteJob(int id);
	public int updateJob(int id, String jobTitle, String description, int status);
}