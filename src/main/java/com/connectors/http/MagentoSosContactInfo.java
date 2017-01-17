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
import com.tools.data.salesOnSpeed.CustomFields;
import com.tools.data.salesOnSpeed.Flag_contact_booster;
import com.tools.data.salesOnSpeed.Flag_member;
import com.tools.data.salesOnSpeed.Flag_parties;
import com.tools.data.salesOnSpeed.Follow_up_date;
import com.tools.data.salesOnSpeed.Is_distributed;
import com.tools.data.salesOnSpeed.Lang_issues;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.salesOnSpeed.Male;
import com.tools.data.salesOnSpeed.Not_interested;
import com.tools.data.salesOnSpeed.PrimaryPhone;
import com.tools.data.salesOnSpeed.Roadshow_city;
import com.tools.data.salesOnSpeed.SalesOnSpeedContactModel;
import com.tools.data.salesOnSpeed.Signup_issues;
import com.tools.data.salesOnSpeed.Underaged;
import com.tools.data.salesOnSpeed.Wrong_details;
import com.tools.data.soap.DBOrderModel;

public class MagentoSosContactInfo {
	public static MagentoSOSContactModel getContactInfo(String contactId) {

		MagentoSOSContactModel contactInfo = new MagentoSOSContactModel();

		try {
			SOAPMessage response = soapGetContactInfo(contactId);
			System.out.println(response);
			try {
				contactInfo = extractContactInfoData(response);
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

	public static SOAPMessage soapGetContactInfo(String userId)
			throws SOAPException, IOException {
		String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		 SOAPMessage soapResponse =
		 soapConnection.call(getContactInfoRequest(sessionId,
				 userId), EnvironmentConstants.SOAP_URL + UrlConstants.API_URI);


//		SOAPMessage soapResponse = soapConnection.call(getOrdersInfoRequest(sessionId, orderIncrementId),
//				"https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

//		 SOAPMessage soapResponse =
//		 soapConnection.call(getOrdersInfoRequest(sessionId,
//		 orderIncrementId),
//		 "https://pippajean-upgrade.evozon.com/" + UrlConstants.API_URI);

		return soapResponse;
	}

	private static SOAPMessage getContactInfoRequest(String ssID, String userId)
			throws SOAPException, IOException {
		SOAPMessage soapMessage = HttpSoapConnector.createSoapDefaultMessage();

		SOAPBody soapBody = soapMessage.getSOAPPart().getEnvelope().getBody();
		SOAPElement getContactRequestParam = soapBody.addChildElement("sosContactInfoRequestParam",
				SoapKeys.URN_PREFIX);

		SOAPElement sessionID = getContactRequestParam.addChildElement(SoapKeys.SESSION_ID);
		sessionID.addTextNode(ssID);

		SOAPElement contactId = getContactRequestParam.addChildElement("contactId");
		contactId.addTextNode(userId);


		soapMessage.saveChanges();

		System.out.print("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	private static MagentoSOSContactModel extractContactInfoData(SOAPMessage response) throws Exception {

		MagentoSOSContactModel model = new MagentoSOSContactModel();
		NodeList result = response.getSOAPBody().getElementsByTagName("result");
		
		NodeList resultNodes = result.item(0).getChildNodes();
		for (int r = 0; r < resultNodes.getLength(); r++) {
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("telephone")) {
				
				model.setPrimaryPhoneNumber(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("firstname")) {
				model.setPrename(resultNodes.item(r).getTextContent());
			}
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("street")) {
				model.setStreet(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("lastname")) {
				model.setLastname(resultNodes.item(r).getTextContent());
			}
			
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("area_code")) {
				model.setPostcode(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("city")) {
				model.setCity(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("country_code")) {
				model.setCountry(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("sos_id")) {
				model.set_id(resultNodes.item(r).getTextContent());
			}
			
			//should be sos created/updated dates ??? 
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("created_at")) {
				model.setCreated(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_at")) {
				model.setUpdated(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("email")) {
				model.setEmail(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("user_id")) {
				model.setUserId(resultNodes.item(r).getTextContent());
			}
			
			//language ???
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("underaged")) {
			
				model.setUnderagedValue(resultNodes.item(r).getTextContent());
				
			}
	
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("not_interesed")) {
				model.setNot_interestedValue(resultNodes.item(r).getTextContent());
			}
			
			// contacted_progress_2;   ????
			//Contacted_progress_3 ???
			//Campaign_name  ??
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("flag_contact_booster")) {
				
				model.setFlagContactBoosterValue(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("wrong_datails")) {
				
				model.setWrongDetailsValue(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("male")) {
			
				model.setMaleValue(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("signup_issues")) {
				
				model.setSignupIssuesValue(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("flag_parties")) {
				
				model.setFlagPartiesValue(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("is_distributed")) {
				
				model.setIsDistributedValue(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("flag_member")) {
			
				model.setFlagMemberValue(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("roadshow_city")) {
				
				model.setRoadshowCityValue(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("follow_up_date")) {
			
				model.setFollowUpDateValue(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("lang_issues")) {
				
				model.setLangIssuesValue(resultNodes.item(r).getTextContent());
			}
			
		
		}
		return model;
	}


	public static void main(String[] args) throws SOAPException, IOException {
		MagentoSOSContactModel dbmodel = MagentoSosContactInfo.getContactInfo("54385");
		System.out.println(dbmodel.toString());
		
	}
}
