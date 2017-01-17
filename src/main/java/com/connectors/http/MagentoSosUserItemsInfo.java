package com.connectors.http;

import java.io.IOException;
import java.util.ArrayList;
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
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSInfoModel;

public class MagentoSosUserItemsInfo {
	public static List<MagentoSOSInfoModel> getUserItemsInfo() {

		List <MagentoSOSInfoModel> userItemsInfo = new ArrayList<MagentoSOSInfoModel>();

		try {
			SOAPMessage response = soapGetUserItemsInfo();
			System.out.println(response);
			try {
				userItemsInfo = extractUserItemsInfoData(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return userItemsInfo;
	}

	public static SOAPMessage soapGetUserItemsInfo() throws SOAPException, IOException {
		String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		SOAPMessage soapResponse = soapConnection.call(getUserItemsInfoRequest(sessionId), EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);

		// SOAPMessage soapResponse =
		// soapConnection.call(getOrdersInfoRequest(sessionId,
		// orderIncrementId),
		// "https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

		// SOAPMessage soapResponse =
		// soapConnection.call(getOrdersInfoRequest(sessionId,
		// orderIncrementId),
		// "https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getUserItemsInfoRequest(String ssID) throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getUserRequestParam = soapBody.addChildElement("sosUserItemsRequestParam", SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getUserRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static List<MagentoSOSInfoModel> extractUserItemsInfoData(SOAPMessage response) throws Exception {

		List<MagentoSOSInfoModel> model = new ArrayList<MagentoSOSInfoModel>();
		
	//	NodeList result = response.getSOAPBody().getElementsByTagName("result");
	//	NodeList itemList = response.getSOAPBody().getElementsByTagName("complexObjectArray");
		NodeList userList = response.getSOAPBody().getElementsByTagName("SOAP-ENC:Struct");

		for (int i = 0; i < userList.getLength(); i++) {
			MagentoSOSInfoModel user = new MagentoSOSInfoModel();
			NodeList resultNodes = userList.item(i).getChildNodes();
			for (int r = 0; r < resultNodes.getLength(); r++) {

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("stylist_id")) {
					user.setStylistId(resultNodes.item(r).getTextContent());
				}
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("stylist_ref")) {
					user.setStylistRef(resultNodes.item(r).getTextContent());
				}
				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("customer_id")) {
					user.setCustomerId(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("stylist_name")) {
					user.setStylistName(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("parent_id")) {
					user.setParentId(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("status")) {
					user.setStatus(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("activated_at")) {
					user.setActivatedAt(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("created_at")) {
					user.setCreatedAt(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_at")) {
					user.setUpdatedAt(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_admin")) {
					user.setUpdatedAdmin(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_commission")) {
					user.setUpdatedCommission(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("can_delete")) {
					user.setCanDelete(resultNodes.item(r).getTextContent());
				}

				if (resultNodes.item(r).getNodeName().equalsIgnoreCase("sos_id")) {
					user.setSosId(resultNodes.item(r).getTextContent());
				}

			}
			model.add(user);
		}
		return model;
	}

	public static void main(String[] args) throws SOAPException, IOException {
		List<MagentoSOSInfoModel> dbmodel = MagentoSosUserItemsInfo.getUserItemsInfo();
	for (MagentoSOSInfoModel magentoSOSInfoModel : dbmodel) {
		System.out.println(magentoSOSInfoModel.toString());
	}
	}
}
