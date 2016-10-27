package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;

import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.persistance.MongoReader;

public class DeleteProduct extends HttpSoapConnector {

	public static SOAPMessage deleteProduct(String customerId) throws SOAPException, IOException {

		String sessID = performLogin();

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(deleteProductMessage(sessID, customerId), MongoReader.getBaseURL() + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage deleteProductMessage(String sessID, String customerId) throws DOMException, SOAPException, IOException {

		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement catalogProductDeleteRequestParam = soapBody.addChildElement(SoapKeys.CATALOG_PRODUCT_DELETE_REQUEST_PARAM, SoapKeys.URN_PREFIX);
		SOAPElement sessionID = catalogProductDeleteRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(sessID);
		SOAPElement customerIdElement = catalogProductDeleteRequestParam.addChildElement(SoapKeys.PRODUCT_ID);
		customerIdElement.addTextNode(customerId);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();
		return soapMessage;
	}

	public static void main(String args[]) throws SOAPException, IOException {
		MagentoProductCalls.deleteApiProduct("19481");
	}

}
