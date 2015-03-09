package com.tools.utils;

import java.util.Random;

public class RandomGenerators {

	static final String AB123 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	public static String randomAlphaNumericString(int len) {

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB123.charAt(rnd.nextInt(AB123.length())));

		return sb.toString();
	}

	public static String randomCapitalLettersString(int len) {

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));

		return sb.toString();
	}

	public static String generateRandomDoubleAsString(int max, int min) {
		Random random = new Random();
		int randomInt = random.nextInt(max - min) + min;		
		return String.valueOf(randomInt) + ".90";		
	}


}
