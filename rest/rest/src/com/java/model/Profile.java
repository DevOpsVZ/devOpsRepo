package com.java.model;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile{

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "skills")
	private String skills;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "cvPath")
	private String cvPath;
	
	public Profile(){
	}

	public Profile(String fullName, String skills, int id, String mobile, String cvPath)
	{
		this.fullName = fullName;
		this.skills = skills;
		this.id = id;
		this.mobile = mobile;
		this.cvPath = cvPath;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setSkills(String skills)
	{
		this.skills = skills;
	}

	public String getSkills(){
		return skills;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId(){
		return id;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public void setCvPath(String cvPath){
		this.cvPath = cvPath;
	}
	
	public String getCvPath(){
		return cvPath;
	}
}