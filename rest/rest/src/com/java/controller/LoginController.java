package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.java.services.LoginService;
import com.java.model.User;

@Controller
public class LoginController{
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = loginService.validateUser(username, password);
		if(user != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			String role = user.getRole();
			if(role.equals("admin")){
				return new ModelAndView("redirect:list.html");
			}else if(role.equals("manager")){
				return new ModelAndView("redirect:manager.html");
			}else if(role.equals("ta")){
				return new ModelAndView("redirect:ta.html");
			}else if(role.equals("screener")){
				return new ModelAndView("screener", "username", user.getUsername());
			}else if(role.equals("interviewer")){
				return new ModelAndView("interviewer", "username", user.getUsername());
			}else{
				return new ModelAndView("index", "error_msg", "Invalid Credentials");
			}
		}
		
		return new ModelAndView("index", "error_msg", "Invalid Credentials");
	}
}