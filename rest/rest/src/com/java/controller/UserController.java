package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import com.java.services.UserService;
import com.java.model.User;

@Controller
public class UserController{
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUsers(){
		String where = "";
		List<User> userList = userService.getList(where);
		return new ModelAndView("admin", "userList", userList);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user){
		userService.addUser(user);
		return new ModelAndView("redirect:list.html");
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUserForManager(@ModelAttribute User user){
		userService.addUser(user);
		return new ModelAndView("redirect:manager.html");
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deleteUser(@RequestParam("id") int id){
		userService.deleteUser(id);
		return new ModelAndView("redirect:list.html");
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUserForManager(@RequestParam("id") int id){
		userService.deleteUser(id);
		return new ModelAndView("redirect:manager.html");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateUser(@RequestParam("update_password")String password, @RequestParam("update_userid") int id){
		userService.updateUser(id, password);
		return new ModelAndView("redirect:list.html");
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUserForManager(@RequestParam("update_password")String password, @RequestParam("update_userid") int id){
		userService.updateUser(id, password);
		return new ModelAndView("redirect:manager.html");
	}
	
	
	
}