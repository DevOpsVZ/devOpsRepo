package com.java.services;

import java.util.List;
import com.java.model.Profile;

public interface ProfileService{
	public List<Profile> getList(String where);
	public int addProfile(Profile profile);
	public int deleteProfile(int id);
	public int updateProfile(int id, String fullName, String skills, String mobile);
}