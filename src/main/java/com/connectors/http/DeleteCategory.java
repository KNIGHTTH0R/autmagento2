package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;

import com.tools.SoapKeys;
import com.tools.env.variables.UrlConstants;

public class DeleteCategory extends HttpSoapConnector {

	public static SOAPMessage deleteCategory(String categoryId) throws SOAPException, IOException {

		String sessID = performLogin();

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//		SOAPMessage soapResponse = soapConnection.call(deleteCategoryMessage(sessID, categoryId), MongoReader.getBaseURL() + UrlConstants.API_URI);
		SOAPMessage soapResponse = soapConnection.call(deleteCategoryMessage(sessID, categoryId), "https://admin-staging-aut.pippajean.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage deleteCategoryMessage(String sessID, String categoryId) throws DOMException, SOAPException, IOException {

		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement customerCustomerDeleteRequestParam = soapBody.addChildElement(SoapKeys.CATEGORY_DELETE_REQUEST, SoapKeys.URN_PREFIX);
		SOAPElement sessionID = customerCustomerDeleteRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(sessID);
		SOAPElement customerIdElement = customerCustomerDeleteRequestParam.addChildElement(SoapKeys.CATEGORY_ID);
		customerIdElement.addTextNode(categoryId);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();
		return soapMessage;
	}

	public static String deleteApiCategory(String categoryId) {

		String resultID = null;
		try {
			SOAPMessage response = deleteCategory(categoryId);
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
