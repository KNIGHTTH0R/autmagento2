package com.tests.uss36.uss36001;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.HttpSoapConnector;
import com.connectors.http.MagentoSosContactInfo;
import com.connectors.http.MagentoSosContactItemsCalls;
import com.connectors.http.MagentoSosUserInfo;
import com.connectors.http.SalesOnSpeedCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.backend.SalesOnSpeed.MagentoToSosContactsSyncSteps;
import com.steps.backend.stylecoach.StylecoachSalesOnSpeedBackendSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.SosContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSUserInfoModel;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
@RunWith(SerenityRunner.class)
public class US36001ValidateContactInfoSyncAfterResetAnActiveUserTest extends BaseTest{
	SosContactModel sosContactModelData = new SosContactModel();
	private List<MagentoSOSContactModel> contactList = new ArrayList<MagentoSOSContactModel>();
	private List<MagentoSOSContactModel> contactsInfo = new ArrayList<MagentoSOSContactModel>();
	private List<MagentoSOSContactModel> sosContactsInfo = new ArrayList<MagentoSOSContactModel>();
	MagentoSOSUserInfoModel sosUserInfo = new MagentoSOSUserInfoModel();
	String sosPassowrd;
	String sosEmail;
	String stylistID;
	String sosUserID;

	MagentoSOSContactModel sosContactInfo = new MagentoSOSContactModel();
	
	@Steps
	MagentoToSosContactsSyncSteps magentoToSosSync;
	
	@Steps
	CustomVerification customVerification;

	
	MagentoSOSUserInfoModel sosUserInfoBeforeReset = new MagentoSOSUserInfoModel();
	
	@Steps 
	public StylecoachSalesOnSpeedBackendSteps stylecoachSalesOnSpeedBackendSteps;
	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabSosContactModel("US36001ResetAnActiveAccountTest").size();
		if (size > 0) {
			sosContactModelData = MongoReader.grabSosContactModel("US36001ResetAnActiveAccountTest").get(0);
		} else{
			System.out.println("The database has no entries");
		}
		
//		sosPassowrd = sosContactModelData.getSosPassword();
//		sosEmail = sosContactModelData.getSosUserEmail();
//		stylistID=sosContactModelData.getStylistId();
//		sosUserID=sosContactModelData.getStylistSosId();
		
		sosUserInfoBeforeReset=MagentoSosUserInfo.getUserInfo("8054");
		String stylistSosId=sosUserInfoBeforeReset.getSosId();
		
		sosPassowrd = "ntpezxlh";
		sosEmail = "fXUXGAmGxoea@yopmail.com";
		stylistID="8054";
		sosUserID=stylistSosId;
		
		
		System.out.println("StylistId " + stylistID);
		System.out.println("sosEmail "+ sosEmail);
		System.out.println("sosPassowrd "+ sosPassowrd);
		System.out.println("sosUserID" + sosUserID);
		
		
		MagentoSOSContactModel contactWithInfo = new MagentoSOSContactModel();

		contactList = MagentoSosContactItemsCalls.getContactItems(stylistID);
		
		String sessionId = HttpSoapConnector.performLogin();
		for (MagentoSOSContactModel contact : contactList) {
			contactWithInfo = MagentoSosContactInfo.getContactInfoSameSess(contact.getContactId(),sessionId);
			
			contactsInfo.add(contactWithInfo);
		}
		
		sosUserInfo = MagentoSosUserInfo.getUserInfo(stylistID);
		sosContactsInfo = SalesOnSpeedCalls.getListCustomerInfo(sosUserInfo.getSosId(), sosEmail, sosPassowrd);
		
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
		
	}

	@Test
	public void us36001ValidateContactInfoSyncAfterResetAnActiveUserTest() throws Exception {
		//check if previous stylist sos id is different than the new one generated 
		magentoToSosSync.validateContactInfoList(contactsInfo, sosContactsInfo);
		customVerification.printErrors();
	}
	
	
}

