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
import org.w3c.dom.NodeList;

import com.tools.constants.Credentials;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;

public class LoginSoapCall {

	/**
	 * This method will login with a user in {@link SoapKeys} and return the
	 * sessionID.
	 * 
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws SOAPException
	 * @throws IOException
	 */
	public static String performLogin() {
		SOAPConnectionFactory soapConnectionFactory = null;
		try {
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
		} catch (UnsupportedOperationException e1) {
			e1.printStackTrace();
		} catch (SOAPException e1) {
			e1.printStackTrace();
		}
		SOAPConnection soapConnection = null;
		try {
			soapConnection = soapConnectionFactory.createConnection();
		} catch (SOAPException e1) {
			e1.printStackTrace();
		}
		SOAPMessage soapResponse = null;
		try {
			soapResponse = soapConnection.call(createLoginRequest(Credentials.LOGIN_USER_SOAP, Credentials.LOGIN_PASS_SOAP), EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);
		} catch (SOAPException e1) {
			e1.printStackTrace();
		}
		String result = "";

		NodeList returnList = null;
		try {
			returnList = soapResponse.getSOAPBody().getElementsByTagName(SoapKeys.RESULT);
		} catch (SOAPException e1) {
			e1.printStackTrace();
		}
		if (returnList.getLength() == 1) {
			result = returnList.item(0).getTextContent();
		}

		System.out.println("SessionID -> " + result);
		System.out.println("Login Response  ");
		try {
			try {
				soapResponse.writeTo(System.out);
			} catch (SOAPException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
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
	public static SOAPMessage createLoginRequest(String user, String pass) {
		SOAPMessage soapMessage = null;
		try {
			soapMessage = createSoapDefaultMessage();
			SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
			SOAPElement loginContainer = soapBody.addChildElement(SoapKeys.LOGIN_PARAM, SoapKeys.URN_PREFIX);
			SOAPElement userBody = loginContainer.addChildElement(SoapKeys.USER_NAME);
			userBody.addTextNode(user);
			SOAPElement apikeyBody = loginContainer.addChildElement(SoapKeys.API_KEY);
			apikeyBody.addTextNode(pass);

			soapMessage.saveChanges();

			soapMessage.writeTo(System.out);
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// SOAP Body

		return soapMessage;
	}

	/**
	 * Create default message with standard envelopes
	 * 
	 * @return
	 * @throws DOMException
	 * @throws SOAPException
	 */
	protected static SOAPMessage createSoapDefaultMessage() throws DOMException, SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		soapMessage.getSOAPPart().getEnvelope().setPrefix(SoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(SoapKeys.URN_PREFIX, SoapKeys.SERVER_URI);
		soapMessage.getSOAPBody().setPrefix(SoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(SoapKeys.SOAP_PREFIX);

		return soapMessage;

	}

}
