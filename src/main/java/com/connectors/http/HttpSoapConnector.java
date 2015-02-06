package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;

import com.tools.data.soap.ProductDetailedModel;

public class HttpSoapConnector {

	public static void main(String args[]) throws Exception {
		// // Create SOAP Connection
		// SOAPConnectionFactory soapConnectionFactory =
		// SOAPConnectionFactory.newInstance();
		// SOAPConnection soapConnection =
		// soapConnectionFactory.createConnection();
		//
		// // Send SOAP Message to SOAP Server
		// String url =
		// "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
		// SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(),
		// url);
		//
		// // print SOAP Response
		// System.out.print("Response SOAP Message:");
		// soapResponse.writeTo(System.out);
		//
		// soapConnection.close();

		createLoginRequest("u", "p");
	}

	/**
	 * Create default message with standard envelopes
	 * 
	 * @return
	 * @throws DOMException
	 * @throws SOAPException
	 */
	private static SOAPMessage createSoapDefaultMessage() throws DOMException, SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		String serverURI = "urn:Magento";
		soapMessage.getSOAPPart().getEnvelope().setPrefix("soapenv");
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration("urn", serverURI);
		soapMessage.getSOAPBody().setPrefix("soapenv");
		soapMessage.getSOAPHeader().setPrefix("soapenv");

		return soapMessage;

	}

	/**
	 * Create a login message for SOAP call.
	 * 
	 * @param user
	 * @param pass
	 * @return
	 * @throws SOAPException
	 * @throws IOException
	 */
	private static SOAPMessage createLoginRequest(String user, String pass) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		// SOAP Body
		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement loginContainer = soapBody.addChildElement("loginParam", "urn");
		SOAPElement userBody = loginContainer.addChildElement("username");
		userBody.addTextNode(user);
		SOAPElement apikeyBody = loginContainer.addChildElement("apikey");
		apikeyBody.addTextNode(pass);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	
	private static SOAPMessage createProduct(ProductDetailedModel product) throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		return soapMessage;
	}
}
