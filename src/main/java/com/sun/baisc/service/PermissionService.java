package com.sun.baisc.service;

import net.sf.json.JSONArray;

public interface PermissionService {

	public JSONArray getMenuByUserName(String userName);
}
