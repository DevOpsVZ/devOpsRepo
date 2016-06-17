package com.java.services;

import com.java.model.User;

public interface LoginService{
	public User validateUser(String username, String password);
}