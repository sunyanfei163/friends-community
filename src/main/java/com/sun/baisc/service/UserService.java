package com.sun.baisc.service;

import java.util.Set;

import com.sun.baisc.model.User;

public interface UserService {

	public User getUserByUserName(String userName);
	
	public Set<String> queryRolesByName(String userName);
}
