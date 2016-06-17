package com.java.model;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job{

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "job_description")
	private String description;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "assignedTo")
	private String assignedTo;
	
	public Job(){
		status = 1;
	}

	public Job(String jobTitle, String description, int id, int status, String assignedTo)
	{
		this.jobTitle = jobTitle;
		this.description = description;
		this.id = id;
		this.status = status;
		this.assignedTo = assignedTo;
	}

	public void setJobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}

	public String getJobTitle(){
		return jobTitle;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId(){
		return id;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
	
	public String getAssignedTo(){
		return assignedTo;
	}
	
	public void setAssignedTo(String assignedTo){
		this.assignedTo = assignedTo;
	}
}