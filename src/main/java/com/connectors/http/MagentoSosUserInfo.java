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
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSInfoModel;

public class MagentoSosUserInfo {
	public static MagentoSOSInfoModel getUserInfo(String userId) {

		MagentoSOSInfoModel contactInfo = new MagentoSOSInfoModel();

		try {
			SOAPMessage response = soapGetUserInfo(userId);
			System.out.println(response);
			try {
				contactInfo = extractUserInfoData(response);
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

	public static SOAPMessage soapGetUserInfo(String userId)
			throws SOAPException, IOException {
		String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		 SOAPMessage soapResponse =
		 soapConnection.call(getUserInfoRequest(sessionId,
				 userId), EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);


//		SOAPMessage soapResponse = soapConnection.call(getOrdersInfoRequest(sessionId, orderIncrementId),
//				"https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

//		 SOAPMessage soapResponse =
//		 soapConnection.call(getOrdersInfoRequest(sessionId,
//		 orderIncrementId),
//		 "https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getUserInfoRequest(String ssID, String userId)
			throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getUserRequestParam = soapBody.addChildElement("sosUserInfoRequestParam",
				SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getUserRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement userStylistId = getUserRequestParam.addChildElement("userId");
		userStylistId.addTextNode(userId);


		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static MagentoSOSInfoModel extractUserInfoData(SOAPMessage response) throws Exception {

		MagentoSOSInfoModel model = new MagentoSOSInfoModel();
		NodeList result = response.getSOAPBody().getElementsByTagName("result");
		
		NodeList resultNodes = result.item(0).getChildNodes();
		for (int r = 0; r < resultNodes.getLength(); r++) {
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("stylist_id")) {
				model.setStylistId(resultNodes.item(r).getTextContent());
			}
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("stylist_ref")) {
				model.setStylistRef(resultNodes.item(r).getTextContent());
			}
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("customer_id")) {
				model.setCustomerId(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("stylist_name")) {
				model.setStylistName(resultNodes.item(r).getTextContent());
			}
			
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("parent_id")) {
				model.setParentId(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("status")) {
				model.setStatus(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("activated_at")) {
				model.setActivatedAt(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("created_at")) {
				model.setCreatedAt(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_at")) {
				model.setUpdatedAt(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_admin")) {
				model.setUpdatedAdmin(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_commission")) {
				model.setUpdatedCommission(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("can_delete")) {
				model.setCanDelete(resultNodes.item(r).getTextContent());
			}
	
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("sync_id")) {
				model.setSyncId(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("local_id")) {
				model.setLocalId(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("sos_id")) {
				model.setSosId(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("user_id")) {
				model.setUserId(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("type")) {
				model.setType(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("allow_sync")) {
				model.setAllowSync(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("crdate")) {
				model.setCrDate(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("sync_date")) {
				model.setSyncDate(resultNodes.item(r).getTextContent());
			}
	
		}
		return model;
	}


	public static void main(String[] args) throws SOAPException, IOException {
		MagentoSOSInfoModel dbmodel = MagentoSosUserInfo.getUserInfo("6490");
		System.out.println(dbmodel.toString());
		
	}
}
