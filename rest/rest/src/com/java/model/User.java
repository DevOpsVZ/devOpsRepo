package com.java.model;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User{

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "user_password")
	private String password;
	
	@Column(name= "role")
	private String role;
	
	public User(){
		
	}

	public User(String username, String password, int id, String role)
	{
		this.username = username;
		this.password = password;
		this.id = id;
		this.role = role;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId(){
		return id;
	}
	
	public void setRole(String role){
		this.role = role;
	}
	
	public String getRole(){
		return this.role;
	}
}