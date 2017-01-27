package com.tests.uss36;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.SosContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSUserInfoModel;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US36001ValidateContactInfoSyncInSosAfterResetContactTest extends BaseTest {
	SosContactModel sosContactModelData = new SosContactModel();
	private List<MagentoSOSContactModel> savedContactList = new ArrayList<MagentoSOSContactModel>();
	private List<MagentoSOSContactModel> contactList = new ArrayList<MagentoSOSContactModel>();
	private List<MagentoSOSContactModel> contactsInfo = new ArrayList<MagentoSOSContactModel>();
	private List<MagentoSOSContactModel> sosContactsInfo = new ArrayList<MagentoSOSContactModel>();
	MagentoSOSUserInfoModel sosUserInfo = new MagentoSOSUserInfoModel();
	String sosPassowrd;
	String sosEmail;
	String stylistID;
	String stylistSosId;

	MagentoSOSContactModel sosContactInfo = new MagentoSOSContactModel();

	@Steps
	MagentoToSosContactsSyncSteps magentoToSosSync;

	@Steps
	CustomVerification customVerification;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabSosContactModel("US36001ResetContactButtonActionTest").size();
		if (size > 0) {
			sosContactModelData = MongoReader.grabSosContactModel("US36001ResetContactButtonActionTest").get(0);
		} else
			System.out.println("The database has no entries");

		savedContactList = MongoReader
				.grabMagContactFormModel("US36001ValidateContatInfoSyncInSosAfterResetAcountTest");

		for (MagentoSOSContactModel magentoSOSContactModel2 : savedContactList) {
			System.out.println("sos id " + magentoSOSContactModel2.get_id());
		}
		sosPassowrd = sosContactModelData.getSosPassword();
		sosEmail = sosContactModelData.getSosUserEmail();
		stylistID = sosContactModelData.getStylistId();
	    stylistSosId=sosContactModelData.getStylistSosId();
		
		
		MagentoSOSContactModel contactWithInfo = new MagentoSOSContactModel();

		contactList = MagentoSosContactItemsCalls.getContactItems(stylistID);

		String sessionId = HttpSoapConnector.performLogin();
		for (MagentoSOSContactModel contact : contactList) {
			contactWithInfo = MagentoSosContactInfo.getContactInfoSameSess(contact.getContactId(), sessionId);
			contactsInfo.add(contactWithInfo);
		}

	//	sosUserInfo = MagentoSosUserInfo.getUserInfo(stylistID);
		sosContactsInfo = SalesOnSpeedCalls.getListCustomerInfo(stylistSosId, sosEmail, sosPassowrd);
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us36001ValidateContactInfoSyncInSosAfterResetContactTest() throws Exception {

		magentoToSosSync.validateContactsSosIdAfterReset(savedContactList, contactsInfo);
		magentoToSosSync.validateContactInfoList(contactsInfo, sosContactsInfo);
		customVerification.printErrors();
	}

	@After
	public void saveData() {
	}
}
