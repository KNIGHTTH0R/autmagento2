package com.connectors.http;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class Authentication {

	private static String username = "Tinxit";
	private static String password = "NAV-MAG12#$";

	public static void setAuthenticator() {
		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password.toCharArray());
			}
		});
	}

}
