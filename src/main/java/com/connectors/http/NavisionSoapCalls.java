package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class NavisionSoapCalls {

	public static String getCompanies() {

		String company = null;
		try {
			SOAPMessage response = NavisionSoapConnector.getCompanies();
			company = extractResult(response);
			System.out.println("company name: " + company);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return company;
	}

	private static String extractResult(SOAPMessage response) throws SOAPException, IOException {
		return response.getSOAPBody().getElementsByTagName("return_value").item(0).getFirstChild().getNodeValue();
	}

	public static void main(String args[]) throws SOAPException, IOException {

		System.setProperty("http.proxyHost", "localhost");
		System.setProperty("http.proxyPort", "8080");

		NavisionSoapCalls.getCompanies();
		
		System.setProperty("http.proxyHost", "null");
		System.setProperty("http.proxyPort", "null");
	}

}
