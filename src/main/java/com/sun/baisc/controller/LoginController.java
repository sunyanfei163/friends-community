package com.sun.baisc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.baisc.model.User;
import com.sun.baisc.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/loginPage")
	public String loginPage() {
		return "/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(User user, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken
			= new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			subject.login(usernamePasswordToken);
			logger.info("======µÇÂ½³É¹¦======");
			mv.setViewName("/index");
		} catch (Exception e) {
			logger.info("======µÇÂ¼Òì³£======");
			e.printStackTrace();
			mv.addObject("errorMsg", "ÓÃ»§Ãû»òÃÜÂë´íÎó£¬µÇÂ¼Ê§°Ü");
			mv.setViewName("/403");
		}
		return mv;
	}
	
	@RequiresRoles("admin")
	@RequiresPermissions("admin")
	@RequestMapping(value="/admin")
	public String xxx() {
		return "/admin";
	}
}
