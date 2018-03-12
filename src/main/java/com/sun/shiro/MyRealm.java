package com.sun.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.baisc.model.User;
import com.sun.baisc.service.UserService;

public class MyRealm extends AuthorizingRealm{
	
	private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		logger.info("======用户授权认证======");
		String userName = principalCollection.getPrimaryPrincipal().toString();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		List<String> roles = userService.queryRolesByName(userName);
		Set<String> userRolesSet = new HashSet<String>();
		for (String role : roles) {
			userRolesSet.add(role);
		}
		simpleAuthorizationInfo.setRoles(userRolesSet);
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		logger.info("======用户登录认证======");
		String userName = authenticationToken.getPrincipal().toString();
		User user = userService.getUserByUserName(userName);
		if(user != null) {
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName()
					, user.getPassword(), getName());
			setSession("currentUser", user);
			return authenticationInfo;
		}
		return null;
	}
	
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser) {
			Session session = currentUser.getSession();
			System.out.println("Session默认超时时间为【" + session.getTimeout() + "】毫秒");
			if(null != session) {
				session.setAttribute(key, value);
			}
		}
	}

}
