package com.connectors.http;

import java.util.ArrayList;
import java.util.List;

import com.tools.data.salesOnSpeed.MagentoSOSContactModel;

public class SosRequestTest {
	
	private static List<MagentoSOSContactModel> contactList = new ArrayList<MagentoSOSContactModel>();
	private static List<MagentoSOSContactModel> contactsInfo = new ArrayList<MagentoSOSContactModel>();
	
	
	public static void main(String[] args) {
		
		
		MagentoSOSContactModel contactWithInfo = new MagentoSOSContactModel();
		contactList=MagentoSosContactItemsCalls.getContactItems("6490");
		
		for (MagentoSOSContactModel contact : contactList) {
			contactWithInfo=MagentoSosContactInfo.getContactInfo(contact.getContactId());
			contactsInfo.add(contactWithInfo);
		}
		
		for (MagentoSOSContactModel magentoSOSContactModel : contactsInfo) {
			System.out.println(magentoSOSContactModel.toString());
		}
	}
}
