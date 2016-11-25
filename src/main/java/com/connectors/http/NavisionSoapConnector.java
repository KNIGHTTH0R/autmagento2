package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;

import com.tools.constants.NavSoapKeys;

public class NavisionSoapConnector {

	/**
	 * Create default message with standard envelopes
	 * 
	 * @return
	 * @throws DOMException
	 * @throws SOAPException
	 * @throws IOException
	 */
	protected static SOAPMessage createSoapDefaultMessage() throws DOMException, SOAPException, IOException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		soapMessage.getSOAPPart().getEnvelope().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(NavSoapKeys.URN_PREFIX, NavSoapKeys.SERVER_URI);
		soapMessage.getSOAPBody().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(NavSoapKeys.SOAP_PREFIX);

		return soapMessage;

	}

	private static SOAPMessage getOrdersListRequest(String orderIncrementId) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement readMultiple = soapBody.addChildElement(NavSoapKeys.READ_MULTIPLE, NavSoapKeys.URN_PREFIX);
		SOAPElement filter = readMultiple.addChildElement(NavSoapKeys.FILTER, NavSoapKeys.URN_PREFIX);
		SOAPElement field = filter.addChildElement(NavSoapKeys.FIELD, NavSoapKeys.URN_PREFIX);
		field.addTextNode("No");
		SOAPElement criteria = filter.addChildElement(NavSoapKeys.CRITERIA, NavSoapKeys.URN_PREFIX);
		criteria.addTextNode(orderIncrementId);
		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static SOAPMessage getOrdersList(String filterValue) throws SOAPException, IOException {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(getOrdersListRequest(filterValue), NavSoapKeys.API_URI);

		return soapResponse;
	}

}
