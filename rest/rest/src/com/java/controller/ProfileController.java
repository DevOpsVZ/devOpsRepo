package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.util.HashMap;
import com.java.model.Profile;
import com.java.services.ProfileService;

@Controller
public class ProfileController{
	
	@Autowired
	ProfileService profileService;
	
	@RequestMapping(value = "/uploadProfile", method = RequestMethod.POST)
	public ModelAndView upload(@RequestParam("file") MultipartFile file, @RequestParam("fullName")String fullName, @RequestParam("skills")String skills,
								@RequestParam("mobile")String mobile){
		
		String fileName = null;
		if(!file.isEmpty()){
			try{
				fileName = file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				File f = new File("..\\webapps\\rest\\cvs\\" + fileName);
				System.out.println(f);
				FileOutputStream fout = new FileOutputStream(f);
				BufferedOutputStream buffStream = new BufferedOutputStream(fout);
				buffStream.write(bytes);
				buffStream.close();
				Profile profile = new Profile();
				profile.setFullName(fullName);
				profile.setSkills(skills);
				profile.setMobile(mobile);
				profile.setCvPath(f.toString());
				profileService.addProfile(profile);
				
				System.out.println(System.getProperty("user.dir"));
				
			}catch(Exception e){
				System.out.println("Failed to upload file " + fileName + "\n " + e.getMessage());
			}
		}
		return new ModelAndView("redirect:profiles.html");
	}
	
	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	public ModelAndView list(){
			List<Profile> profileList = null;
			try{
				String where = "";
				profileList = profileService.getList(where);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			return new ModelAndView("profiles", "profileList", profileList);
	}
	
	@RequestMapping(value = "/deleteProfile", method = RequestMethod.POST)
	public ModelAndView deleteProfile(@RequestParam("id") int id){
		profileService.deleteProfile(id);
		return new ModelAndView("redirect:profiles.html");
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ModelAndView updateProfile(@RequestParam("updateFullName")String fullName, @RequestParam("updateSkills") String skills,
						@RequestParam("updateMobile") String mobile, @RequestParam("id") int id){
		profileService.updateProfile(id, fullName, skills, mobile);
		return new ModelAndView("redirect:list.html");
	}
}