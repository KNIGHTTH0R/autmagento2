package com.tests.uss35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoSosContactInfo;
import com.connectors.http.MagentoSosContactItemsCalls;
import com.connectors.http.NavisionSoapCalls;
import com.connectors.http.OrdersInfoMagentoCalls;
import com.steps.backend.ImportOrdersSteps;
import com.tools.CustomVerification;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.soap.DBOrderModel;
import com.tools.data.soap.NavOrderModel;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;

@Story(Application.ValidateOrderImport.US34_1.class)
@RunWith(SerenityRunner.class)
public class US35SosAplicatiionReguestsTest {
	
	
	
	private List<MagentoSOSContactModel> contactList = new ArrayList<MagentoSOSContactModel>();
	private List<MagentoSOSContactModel> contactsInfo = new ArrayList<MagentoSOSContactModel>();
	

	
	@Before
	public void setUp() throws Exception {

		

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
	
	
	@Test
	public void us35001SosAplicatiionReguestsTest() throws Exception {
		//ordersImport.validateOrders(shopListOrders, navListOrders);
	//	customVerification.printErrors();
	}
}
