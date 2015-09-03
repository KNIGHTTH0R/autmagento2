package com.connectors.http;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.DOMException;

import com.sun.jersey.core.util.Base64;
import com.tools.NavSoapKeys;

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

		// soapMessage.getMimeHeaders().addHeader("Authorization",
		// "NTLM TlRMTVNTUAADAAAAGAAYAHAAAABSAVIBiAAAAAAAAABYAAAADAAMAFgAAAAMAAwAZAAAAAAAAADaAQAABYKIogYBsR0AAAAPkTY2fE1Fsshz9L+y1pImomIAYQByAG0AMAAxAEUAVgBPADAAMgA2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHCQYsMp9sbDUYkRw+J1YiQBAQAAAAAAAGl5OTd25dABl9A1M1ipeY4AAAAAAgAeAE8AVwBOAEUAUgBPAFIALQBPAFYARABIADEASgBFAAEAHgBPAFcATgBFAFIATwBSAC0ATwBWAEQASAAxAEoARQAEAB4ATwBXAE4ARQBSAE8AUgAtAE8AVgBEAEgAMQBKAEUAAwAeAE8AVwBOAEUAUgBPAFIALQBPAFYARABIADEASgBFAAcACABpeTk3duXQAQYABAACAAAACAAwADAAAAAAAAAAAQAAAAAgAADUNl++PjMqjiz5p4TfREwsnSGrwRWpWWEy7Q51Cs8nFAoAEAAAAAAAAAAAAAAAAAAAAAAACQAyAEgAVABUAFAALwAxADQAOAAuADIANQAxAC4AMQA3ADgALgAyADAANwA6ADcAMAA2ADEAAAAAAAAAAAAAAAAA");

		soapMessage.getSOAPPart().getEnvelope().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPPart().getEnvelope().removeNamespaceDeclaration("SOAP-ENV");
		soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration(NavSoapKeys.URN_PREFIX, NavSoapKeys.SERVER_URI);
		soapMessage.getSOAPBody().setPrefix(NavSoapKeys.SOAP_PREFIX);
		soapMessage.getSOAPHeader().setPrefix(NavSoapKeys.SOAP_PREFIX);

		return soapMessage;

	}

	private static SOAPMessage getComp() throws SOAPException, IOException {
		SOAPMessage soapMessage = createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		soapBody.addChildElement(NavSoapKeys.COMPANIES, NavSoapKeys.URN_PREFIX);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	public static SOAPMessage getCompanies() throws SOAPException, IOException {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		SOAPMessage soapResponse = soapConnection.call(getComp(), NavSoapKeys.API_URI);

		return soapResponse;
	}

}
