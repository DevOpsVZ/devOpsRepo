package com.java.dao;

import com.java.model.Profile;
import java.util.List;

public interface ProfileDao{
	public List<Profile> getList(String where);
	public int createProfile(Profile profile);
	public int deleteProfile(int id);
	public int updateProfile(int id, String fullName, String skills, String mobile);
}