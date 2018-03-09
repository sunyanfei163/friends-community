package com.sun.baisc.service.impl;

import com.sun.baisc.dao.UserDao;
import com.sun.baisc.model.User;
import com.sun.baisc.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserByUserName(String userName) {
		return userDao.getByUserName(userName);
	}
}
