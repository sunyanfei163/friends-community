package com.sun.baisc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.baisc.service.PermissionService;

import net.sf.json.JSONArray;

@Controller
public class PermissionController {
	
	@Autowired
	private PermissionService  permissionService;

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	@RequestMapping("/getMenu")
	@ResponseBody
	public String getMenu(String userName) {
		JSONArray jsonArr = permissionService.getMenuByUserName(userName);
		return jsonArr.toString();
	}
}
