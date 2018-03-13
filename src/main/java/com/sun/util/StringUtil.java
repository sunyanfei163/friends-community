package com.sun.util;

import java.util.Random;

public class StringUtil {
	private static final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	public static String getRandomStr(int length) {
		String result = "";
		Random r = new Random();
		if(length <= 0) {
			return "";
		}
		for (int i = 0; i < length; i++) {
			char[] chars = str.toCharArray();
			result += chars[r.nextInt(62)];
		}
		return result;
	}
}
