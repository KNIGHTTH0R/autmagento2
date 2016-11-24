package com.connectors.http;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class NavisionSoapCalls {
	
	private static String thePassword = "NAV-MAG12#$";

	public static String getOrderDetails(String incrementId) {

		String company = null;
		try {
			SOAPMessage response = NavisionSoapConnector.getOrderDetails(incrementId);
			company = extractResult(response);
			System.out.println("result: " + company);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return company;
	}

	private static String extractResult(SOAPMessage response) throws SOAPException, IOException {
		return response.getSOAPBody().getElementsByTagName("No").item(0).getFirstChild().getNodeValue();
	}
	


	private static void setAuthenticator() {
		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("Tinxit", thePassword.toCharArray());
			}
		});
	}

	public static void main(String args[]) throws SOAPException, IOException {

		
//		setAuthenticator();

		System.setProperty("http.proxyHost", "localhost");
		System.setProperty("http.proxyPort", "8080");

		NavisionSoapCalls.getOrderDetails("10023578700");
		
		System.setProperty("http.proxyHost", "null");
		System.setProperty("http.proxyPort", "null");
	}

}
