package com.sun.baisc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sun.baisc.dao.UserDao;
import com.sun.baisc.model.User;
import com.sun.baisc.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserByUserName(String userName) {
		return userDao.getByUserName(userName);
	}

	public List<String> queryRolesByName(String userName) {
		return userDao.queryRolesByName(userName);
	}
}
