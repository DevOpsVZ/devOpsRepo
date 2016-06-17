package com.java.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.dao.LoginDao;
import com.java.model.User;


public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;
	
	public User validateUser(String username, String password){
		return loginDao.validateUser(username, password);
	}
}