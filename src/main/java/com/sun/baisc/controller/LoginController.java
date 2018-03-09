package com.sun.baisc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.baisc.model.User;
import com.sun.baisc.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(User user, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User currentUser = userService.getUserByUserName(user.getUserName());
		if(currentUser != null) {
			
		}else {
		}
		return mv;
	}
}
