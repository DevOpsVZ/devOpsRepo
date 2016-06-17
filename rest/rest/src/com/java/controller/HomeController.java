package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import com.java.services.UserService;
import com.java.services.JobService;
import com.java.model.User;
import com.java.model.Job;
@Controller
public class HomeController{
	
	@Autowired
	UserService userService;
	
	@Autowired
	JobService jobService;
	
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView homepage(HttpServletRequest request){
		String where = "where role not in('admin','manager')";
		List<User> userList = userService.getList(where);
		return new ModelAndView("manager", "userList", userList);
	}
	
	@RequestMapping(value = "/ta", method = RequestMethod.GET)
	public ModelAndView homepageTA(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String where = "where assignedTo = '" + user.getId() +  "'";
		List<Job> jobList = jobService.getList(where);
		
		return new ModelAndView("ta", "jobList", jobList);
	}
	
}