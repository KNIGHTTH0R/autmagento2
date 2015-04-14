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
import com.tools.env.stagingaut.UrlConstants;

public class DeleteCustomer extends HttpSoapConnector {

	public static void main(String args[]) throws SOAPException, IOException {
		deleteCustomer("26236");
	}

	public static SOAPMessage deleteCustomer(String customerId) throws SOAPException, IOException {
		String sessID = HttpSoapConnector.performLogin();

		System.out.println("Sesion id :" + sessID);
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(deleteCustomerMessage(sessID, customerId), UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage deleteCustomerMessage(String sessID, String customerId) throws DOMException, SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement customerCustomerDeleteRequestParam = soapBody.addChildElement(SoapKeys.CUSTOMER_CUSTOMER_DELETE_REQUEST_PARAM, SoapKeys.URN_PREFIX);
		SOAPElement sessionID = customerCustomerDeleteRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(sessID);
		SOAPElement customerIdElement = customerCustomerDeleteRequestParam.addChildElement(SoapKeys.CUSTOMER_ID);
		customerIdElement.addTextNode(customerId);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();
		return soapMessage;
	}

}
