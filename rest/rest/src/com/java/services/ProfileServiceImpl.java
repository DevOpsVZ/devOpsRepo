package com.java.services;

import java.util.List;
import com.java.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import com.java.dao.ProfileDao;

public class ProfileServiceImpl implements ProfileService{
	
	@Autowired
	ProfileDao profileDao;
	
	public List<Profile> getList(String where){
		return profileDao.getList(where);
	}
	
	public int addProfile(Profile profile){
		return profileDao.createProfile(profile);
	}
	
	public int deleteProfile(int id){
		return profileDao.deleteProfile(id);
	}
	public int updateProfile(int id, String fullName, String skills, String mobile){
		return profileDao.updateProfile(id, fullName, skills, mobile);
	}
}