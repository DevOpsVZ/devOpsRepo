package com.java.dao;

import com.java.model.User;

public interface LoginDao{
	public User validateUser(String username, String password);
}