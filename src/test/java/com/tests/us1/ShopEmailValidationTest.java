package com.tests.us1;

import java.util.List;

import com.connectors.gmail.GmailConnector;
import com.tools.PrintUtils;
import com.tools.data.EmailModel;

public class ShopEmailValidationTest {

	
	public static void main(String args[]){
		List<EmailModel> emailList = GmailConnector.readGmail();
		PrintUtils.printEmailList(emailList);
	}
}
