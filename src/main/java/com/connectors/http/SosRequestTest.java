package com.connectors.http;

import com.tools.CustomVerification;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSUserInfoModel;



public class SosRequestTest {
	
	/*private static List<MagentoSOSContactModel> contactList = new ArrayList<MagentoSOSContactModel>();
	private static List<MagentoSOSContactModel> contactsInfo = new ArrayList<MagentoSOSContactModel>();*/
	

	
	
	public static void main(String[] args) throws Exception {
		
		
		MagentoSOSContactModel contactWithInfo = new MagentoSOSContactModel();
		MagentoSOSUserInfoModel sosUserInfo =new MagentoSOSUserInfoModel();
		MagentoSOSContactModel sosContactInfo=new MagentoSOSContactModel();
//		contactList=MagentoSosContactItemsCalls.getContactItems("6490");
//		
//		for (MagentoSOSContactModel contact : contactList) {
//			contactWithInfo=MagentoSosContactInfo.getContactInfo(contact.getContactId());
//			contactsInfo.add(contactWithInfo);
//		}
		
		contactWithInfo=MagentoSosContactInfo.getContactInfo("56267");
		sosUserInfo=MagentoSosUserInfo.getUserInfo(contactWithInfo.getUserId());
		
		sosContactInfo=SalesOnSpeedCalls.getCustomerInfo(contactWithInfo.get_id(), sosUserInfo.getSosId(),"","");
		
		
		
		
		
		validatePrename(contactWithInfo.getPrename(),sosContactInfo.getPrename());
		validateLastName(contactWithInfo.getLastname(),sosContactInfo.getLastname());
		validateCity(contactWithInfo.getCity(),sosContactInfo.getCity());
		validateCountry(contactWithInfo.getCountry(),sosContactInfo.getCountry());
		//validatePostCode(contactWithInfo.getPostcode(),sosContactInfo.getPostcode());
		
	}
	


	public static void validatePrename(String customerID, String compare) {
		CustomVerification.verifyTrue("Failure: Prename doesn't match: " + customerID + " - " + compare,
				customerID.contentEquals(compare));
	}

	
	public static void validateLastName(String orderType, String compare) {
		CustomVerification.verifyTrue("Failure: LastName doesn't match: " + orderType + " - " + compare,
				orderType.contentEquals(compare));
	}


	public static  void validateCity(String orderType, String compare) {
		CustomVerification.verifyTrue("Failure: City doesn't match: " + orderType + " - " + compare,
				orderType.contentEquals(compare));
	}


	public static void validateCountry(String orderId, String compare) {
		CustomVerification.verifyTrue("Failure: City doesn't match: " + orderId + " - " + compare,
				orderId.contentEquals(compare));
	}
	
/*	private static void validatePostCode(String postcode, String postcode2) {
		CustomVerification.verifyTrue("Failure: Prename doesn't match: " + postcode + " - " + postcode2,
				postcode.contentEquals(postcode2));		
	}*/
}
