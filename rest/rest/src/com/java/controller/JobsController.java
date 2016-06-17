package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import com.java.services.JobService;
import com.java.services.UserService;
import com.java.model.Job;
import com.java.model.User;
import java.util.HashMap;

@Controller
public class JobsController{
	
	@Autowired
	JobService jobService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public ModelAndView homepage(){
		
		ModelAndView mv = new ModelAndView("jobs");
		String where = "";
		List<Job> jobsList = jobService.getList(where);
	
		where = "where role = 'ta'";
		List<User> taList = userService.getList(where);
	
		mv.addObject("jobsList", jobsList);
		mv.addObject("taList", taList);
	
		return mv;
	}
	
	@RequestMapping(value = "/createJob", method = RequestMethod.POST)
	public ModelAndView createJob(@ModelAttribute Job job){

		int id = jobService.createJob(job);
		return new ModelAndView("redirect:jobs.html");
	}
	
	@RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
	public ModelAndView deleteJob(@RequestParam("id") int id){

		int sid = jobService.deleteJob(id);
		return new ModelAndView("redirect:jobs.html");
	}
	
	public HashMap<Integer, String> taNames(List<User> users){
		HashMap<Integer, String> list = new HashMap<Integer, String>();
		for(User user: users){
			list.put(user.getId(), user.getUsername());
		}
		return list;
	}
}