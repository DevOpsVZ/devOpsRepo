package com.java.dao;

import java.util.List;
import com.java.model.User;

public interface UserDao{
	public List<User> getList(String where);
	public int addUser(User user);
	public int deleteUser(int id);
	public int updateUser(int id, String password);
}