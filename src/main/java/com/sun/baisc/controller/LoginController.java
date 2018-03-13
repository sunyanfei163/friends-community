package com.sun.baisc.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.baisc.model.User;
import com.sun.baisc.service.UserService;
import com.sun.util.ValidateCode;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/registerPage")
	public String registerPage() {
		return "/register";
	}
	
	@RequestMapping(value="/loginPage")
	public String loginPage() {
		return "/login";
	}
	
	@RequestMapping("/register")
	public String register(User user, HttpServletRequest request, ModelMap mm) {
		String passwordValid = WebUtils.getCleanParam(request, "passwordValid");
		String targetUrl = "/loginPage";
		if(!passwordValid.equals(user.getPassword())) {
			mm.addAttribute("errorMsg", "ÃÜÂë²»Ò»ÖÂ£¡");
			targetUrl = "/registerPage";
		}
		try {
			userService.register(user);
		} catch (Exception e) {
			logger.info(e.getMessage());
			mm.addAttribute("errorMsg", "×¢²áÊ§°Ü");
			targetUrl = "/registerPage";
		}
		return targetUrl;
	}
	
	@RequestMapping("/validateCode")
	public void validateCode(HttpServletRequest request, HttpServletResponse response)
					throws IOException{
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_ALL_MIX, 4, "iIoO");
		request.getSession().setAttribute("validateCode", verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 45, 5, true
				, Color.WHITE, Color.BLUE, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}
	
	@RequestMapping(value="/login", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView login(User user, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Session session = SecurityUtils.getSubject().getSession();
		String code = (String)session.getAttribute("validateCode");
		session.removeAttribute("validateCode");
		String submitCode = WebUtils.getCleanParam(request, "validateCode");
		if(StringUtils.isEmpty(submitCode) 
				|| !StringUtils.equals(code.toLowerCase(), submitCode.toLowerCase())) {
			mv.addObject("errorMsg_validateCode", "ÑéÖ¤Âë´íÎó");
			mv.setViewName("/login");
			return mv;
		}
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
			mv.addObject("errorMsg_validateCode", "ÓÃ»§Ãû»òÃÜÂë´íÎó£¬µÇÂ¼Ê§°Ü");
			mv.setViewName("/login");
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
