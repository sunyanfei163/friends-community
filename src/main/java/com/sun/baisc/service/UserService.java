package com.sun.baisc.service;

import java.util.List;

import com.sun.baisc.model.Permission;
import com.sun.baisc.model.User;

public interface UserService {

	public User getUserByUserName(String userName);
	
	public List<String> queryRolesByName(String userName);
	
	public List<Permission> queryPermissionsByName(String userName);
	
	public boolean register(User user);
}
