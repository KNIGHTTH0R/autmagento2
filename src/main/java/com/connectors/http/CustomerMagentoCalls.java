package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * @author mihaibarta
 *
 */

public class CustomerMagentoCalls {

	public static String deleteApiProduct(String customerId) {

		String resultID = null;
		try {
			SOAPMessage response = DeleteCustomer.deleteCustomer(customerId);
			resultID = extractResult(response);
			System.out.println("resultID: " + resultID);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultID;
	}

	private static String extractResult(SOAPMessage response) throws SOAPException, IOException {
		return response.getSOAPBody().getElementsByTagName("result").item(0).getFirstChild().getNodeValue();
	}

}
