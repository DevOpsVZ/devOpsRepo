package com.java.dao;

import java.util.List;
import com.java.model.Job;

public interface JobDao{
	public List<Job> getList(String where);
	public int createJob(Job job);
	public int deleteJob(int id);
	public int updateJob(int id, String title, String description, int status);
}