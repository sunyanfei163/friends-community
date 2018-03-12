package com.sun.baisc.dao;

import java.util.List;

import com.sun.baisc.model.User;

public interface UserDao {

	public User getByUserName(String userName);
	
	public List<String> queryRolesByName(String userName);
}
