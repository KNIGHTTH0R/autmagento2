package com.connectors.http;

import java.io.IOException;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

import com.tools.constants.EnvironmentConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.CategoryModel;

/**
 * @author mihaibarta
 *
 */

public class MagentoCategoriesInfoCalls {

	public static CategoryModel getCategoryInfo(String contactId) {

		CategoryModel contactInfo = new CategoryModel();

		try {
			SOAPMessage response = soapGetCategoryInfo(contactId);
			System.out.println(response);
			try {
				contactInfo = extractCotegoryInfoData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contactInfo;
	}

	public static SOAPMessage soapGetCategoryInfo(String categoryId)
			throws SOAPException, IOException {
		String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		 SOAPMessage soapResponse =
		 soapConnection.call(getCategoryInfoRequest(sessionId,
				 categoryId), EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);

		return soapResponse;
	}
	
	


	private static SOAPMessage getCategoryInfoRequest(String ssID, String category)
			throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getContactRequestParam = soapBody.addChildElement("catalogCategoryInfoRequestParam",
				SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getContactRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement categoryId = getContactRequestParam.addChildElement("categoryId");
		categoryId.addTextNode(category);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static CategoryModel extractCotegoryInfoData(SOAPMessage response) throws Exception {

		CategoryModel model = new CategoryModel();
		NodeList result = response.getSOAPBody().getElementsByTagName("result");
		
		
		
		NodeList resultNodes = result.item(0).getChildNodes();
		for (int r = 0; r < resultNodes.getLength(); r++) {
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("name")) {
				model.setName(resultNodes.item(r).getTextContent());
			}
			
		}
		
		return model;
	}
	
	public static void main(String[] args) {
		CategoryModel model=getCategoryInfo("104");
		
		System.out.println(model.getName());
	}
}
