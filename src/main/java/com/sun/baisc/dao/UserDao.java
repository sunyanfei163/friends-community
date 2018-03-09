package com.sun.baisc.dao;

import java.util.Set;

import com.sun.baisc.model.User;

public interface UserDao {

	public User getByUserName(String userName);
	
	public Set<String> queryRolesByName(String userName);
}
