package com.connectors.http;

import java.io.IOException;
import java.util.Locale;

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
import com.tools.persistance.MongoReader;
import com.tools.utils.DateUtils;

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
	
	
	
	
	public static MagentoSOSContactModel getContactInfoSameSess(String contactId,String sessID) {

		MagentoSOSContactModel contactInfo = new MagentoSOSContactModel();

		try {
			SOAPMessage response = soapGetContactInfoSameSess(contactId,sessID);
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

	public static SOAPMessage soapGetContactInfoSameSess(String userId,String sessID)
			throws SOAPException, IOException {
	//	String sessionId = HttpSoapConnector.performLogin();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		 SOAPMessage soapResponse =
		 soapConnection.call(getContactInfoRequest(sessID,
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
		
		String phoneNumber="";
		String areaCode="";
		String houseNumber="";
		String street="";
		
		NodeList resultNodes = result.item(0).getChildNodes();
		for (int r = 0; r < resultNodes.getLength(); r++) {
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("telephone")) {
				phoneNumber=resultNodes.item(r).getTextContent();
			//	model.setPrimaryPhoneNumber(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("firstname")) {
				model.setPrename(resultNodes.item(r).getTextContent());
			}
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("street")) {
			//	model.setStreet(resultNodes.item(r).getTextContent());
				street=resultNodes.item(r).getTextContent();
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("lastname")) {
				model.setLastname(resultNodes.item(r).getTextContent());
			}
			
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("house_number")) {
				model.setHouseNumber(resultNodes.item(r).getTextContent());
				houseNumber=resultNodes.item(r).getTextContent();
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("postcode")) {
				model.setPostcode(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("area_code")) {
//			/	model.setAreaCode(resultNodes.item(r).getTextContent());
				areaCode=resultNodes.item(r).getTextContent();
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
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("contacted_progress")) {
				model.setContacted_progress(resultNodes.item(r).getTextContent());
			}
			
			//should be sos created/updated dates ??? 
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("created_at")) {
//				model.setCreated(DateUtils.parseDate(resultNodes.item(r).getTextContent(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
//						new Locale.Builder().setLanguage(MongoReader.getContext()).build()));
				
				
				model.setCreated(DateUtils.parseDate(resultNodes.item(r).getTextContent(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
						new Locale.Builder().setLanguage("de").build()));
				//model.setCreated(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("updated_at")) {
				
//				model.setUpdated(DateUtils.parseDate(resultNodes.item(r).getTextContent(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
//						new Locale.Builder().setLanguage(MongoReader.getContext()).build()));
				
				model.setUpdated(DateUtils.parseDate(resultNodes.item(r).getTextContent(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
						new Locale.Builder().setLanguage("de").build()));
				
				
			//	model.setUpdated(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("email")) {
				model.setEmail(resultNodes.item(r).getTextContent());
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("user_id")) {
				model.setUserId(resultNodes.item(r).getTextContent());
			}
			
			//language ???
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("underaged")) {
				String underaged=resultNodes.item(r).getTextContent();
				if(underaged==null){
					underaged="null";
				}else{
					underaged=underaged== "1" ? "true" : "false";
				}
					
				
				model.setUnderagedValue(underaged);
				
			}
	
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("not_interesed")) {
				
				String Not_interested=resultNodes.item(r).getTextContent();
				if(Not_interested==null){
					Not_interested="null";
				}else{
					Not_interested=Not_interested== "1" ? "true" : "false";
				}
				model.setNot_interestedValue(Not_interested);
			}
			
			// contacted_progress_2;   ????
			//Contacted_progress_3 ???
			
			//Campaign_name  ??
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("flag_contact_booster")) {
				String FlagContactBooster=resultNodes.item(r).getTextContent();
				if(FlagContactBooster==null){
					FlagContactBooster="null";
				}else{
					FlagContactBooster=FlagContactBooster== "1" ? "true" : "false";
				}
				model.setFlagContactBoosterValue(FlagContactBooster);
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("wrong_datails")) {
				String WrongDetails=resultNodes.item(r).getTextContent();
				if(WrongDetails==null){
					WrongDetails="null";
				}else{
					WrongDetails=WrongDetails== "1" ? "true" : "false";
				}
				model.setWrongDetailsValue(WrongDetails);
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("male")) {
				String MaleValue=resultNodes.item(r).getTextContent();
				if(MaleValue==null){
					MaleValue="null";
				}else{
					MaleValue=MaleValue== "1" ? "true" : "false";
				}
				
				model.setMaleValue(MaleValue);
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("signup_issues")) {
				
				String SignupIssues=resultNodes.item(r).getTextContent();
				if(SignupIssues==null){
					SignupIssues="null";
				}else{
					SignupIssues=SignupIssues== "1" ? "true" : "false";
				}
				
				model.setSignupIssuesValue(SignupIssues);
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("flag_parties")) {
				
				String FlagParties=resultNodes.item(r).getTextContent();
				if(FlagParties==null){
					FlagParties="null";
				}else{
					FlagParties=FlagParties== "1" ? "true" : "false";
				}
				
				model.setFlagPartiesValue(FlagParties);
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("is_distributed")) {
				String IsDistributed=resultNodes.item(r).getTextContent();
				if(IsDistributed==null){
					IsDistributed="null";
				}else{
					IsDistributed=IsDistributed== "1" ? "true" : "false";
				}
				
				
				model.setIsDistributedValue(IsDistributed);
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("flag_member")) {
			
				String FlagMember=resultNodes.item(r).getTextContent();
				if(FlagMember==null){
					FlagMember="null";
				}else{
					FlagMember=FlagMember== "1" ? "true" : "false";
				}
				
				model.setFlagMemberValue(FlagMember);
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("roadshow_city")) {
				
				String RoadshowCityV=resultNodes.item(r).getTextContent();
				if(RoadshowCityV==null){
					RoadshowCityV="null";
				}else{
					RoadshowCityV=RoadshowCityV== "No registration" ? "" : RoadshowCityV ;
				}
				
				model.setRoadshowCityValue(RoadshowCityV);
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("follow_up_date")) {
				String followUpDate=resultNodes.item(r).getTextContent();
				if(followUpDate==""){
					followUpDate="0";
				}
				
				model.setFollowUpDateValue(followUpDate);
			}
			
			if (resultNodes.item(r).getNodeName().equalsIgnoreCase("lang_issues")) {
				
				String LangIssues=resultNodes.item(r).getTextContent();
				if(LangIssues==null){
					LangIssues="null";
				}else{
					LangIssues=LangIssues== "1" ? "true" : "false";
				}
				
				model.setLangIssuesValue(LangIssues);
			}
			
		
		}
		
		model.setPrimaryPhoneNumber(areaCode.concat(phoneNumber));
		model.setStreet(street+" "+houseNumber);
		return model;
	}


	public static void main(String[] args) throws SOAPException, IOException {
		MagentoSOSContactModel dbmodel = MagentoSosContactInfo.getContactInfo("56267");
		System.out.println(dbmodel.toString());
		
	}
}
