package com.sun.baisc.service.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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
	
	public boolean register(User user) {
		user = encryption(user);
		
		return true;
	}

	private User encryption(User user) {
		ByteSource salt = ByteSource.Util.bytes(user.getUserName() + user.getSalt());
		
		String newPs = new SimpleHash("MD5", user.getPassword(), salt, 1024).toHex();
		user.setPassword(newPs);
		return user;
	}
	
	public static void main(String[] args) {
		UserServiceImpl service = new UserServiceImpl();
		User user = new User("shuai", "1");
		user.setSalt("123");
		System.out.println(service.encryption(user));
		user.setSalt("321");
		System.out.println(service.encryption(user));
	}
}
