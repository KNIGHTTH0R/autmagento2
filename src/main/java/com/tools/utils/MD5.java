package com.tools.utils;

import java.security.*;
import java.math.*;

public class MD5 {

	public static String getMd5(String plainString) throws Exception {

		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(plainString.getBytes(), 0, plainString.length());

		return new BigInteger(1, m.digest()).toString(16);
	}

}