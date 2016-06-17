package com.java.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.java.model.User;
import com.java.dao.UserDao;

public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	public List<User> getList(String where){
		return userDao.getList(where);
	}
	
	public int addUser(User user)
	{
		return userDao.addUser(user);
	}
	
	public int deleteUser(int id){
		return userDao.deleteUser(id);
	}
	
	public int updateUser(int id, String password){
		return userDao.updateUser(id, password);
	}
}