package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.tools.data.navision.SalesOrderInfoModel;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.salesOnSpeed.SalesOnSpeedContactModel;
import com.tools.data.soap.DBOrderModel;

public class MagentoSosContactItemsCalls {
	
	
	public static List<MagentoSOSContactModel> getContactItems(String userId) {

		List<MagentoSOSContactModel> order = new ArrayList<MagentoSOSContactModel>();

		try {
			SOAPMessage response = soapGetContactItems(userId);
			System.out.println(response);
			try {
				order = extractOrderInfoData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return order;
	}

	public static SOAPMessage soapGetContactItems(String userId)
			throws SOAPException, IOException {
		String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		 SOAPMessage soapResponse =
		 soapConnection.call(getContactItemsRequest(sessionId,
				 userId), EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);


//		SOAPMessage soapResponse = soapConnection.call(getOrdersInfoRequest(sessionId, orderIncrementId),
//				"https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

//		 SOAPMessage soapResponse =
//		 soapConnection.call(getOrdersInfoRequest(sessionId,
//		 orderIncrementId),
//		 "https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getContactItemsRequest(String ssID, String userId)
			throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getStylistRequestParam = soapBody.addChildElement("sosContactItemsRequestParam",
				SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getStylistRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement orderIncrementId = getStylistRequestParam.addChildElement("userId");
		orderIncrementId.addTextNode(userId);


		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static List<MagentoSOSContactModel> extractOrderInfoData(SOAPMessage response) throws Exception {

		List<MagentoSOSContactModel> model = new ArrayList<MagentoSOSContactModel>();
		
	

		return model;
	}

	

	public static void main(String[] args) throws SOAPException, IOException {
		 
		
	}
}
