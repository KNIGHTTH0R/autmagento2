package com.tests.uss36;

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
import com.steps.backend.SalesOnSpeed.MagentoToSosContactsSyncSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.SosContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSContactModel;
import com.tools.data.salesOnSpeed.MagentoSOSUserInfoModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US3.1 Shop for myself VAT valid and no SMB billing and shipping AT", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class US36001ValidateContatInfoSyncInSosAfterResetAcountTest extends BaseTest {
	SosContactModel sosContactModelData = new SosContactModel();
	private List<MagentoSOSContactModel> contactList = new ArrayList<MagentoSOSContactModel>();
	private List<MagentoSOSContactModel> contactsInfo = new ArrayList<MagentoSOSContactModel>();
	private List<MagentoSOSContactModel> sosContactsInfo = new ArrayList<MagentoSOSContactModel>();
	MagentoSOSUserInfoModel sosUserInfo = new MagentoSOSUserInfoModel();
	String sosPassowrd;
	String sosEmail;
	String stylistID;

	MagentoSOSContactModel sosContactInfo = new MagentoSOSContactModel();
	
	@Steps
	MagentoToSosContactsSyncSteps magentoToSosSync;

	@Before
	public void setUp() throws Exception {

		int size = MongoReader.grabSosContactModel("US36001SyncBackendMagentoToSos").size();
		if (size > 0) {
			sosContactModelData = MongoReader.grabSosContactModel("US36001SyncBackendMagentoToSos").get(0);
		} else{
			System.out.println("The database has no entries");
		}
		
		sosPassowrd = sosContactModelData.getSosPassword();
		sosEmail = sosContactModelData.getSosUserEmail();
		stylistID=sosContactModelData.getStylistId();
		
		System.out.println("StylistId " + stylistID);
		System.out.println("sosEmail "+ sosEmail);
		System.out.println("sosPassowrd "+ sosPassowrd);
		MagentoSOSContactModel contactWithInfo = new MagentoSOSContactModel();

		contactList = MagentoSosContactItemsCalls.getContactItems(stylistID);

		String sessionId = HttpSoapConnector.performLogin();
		for (MagentoSOSContactModel contact : contactList) {
			contactWithInfo = MagentoSosContactInfo.getContactInfoSameSess(contact.getContactId(),sessionId);
			contactsInfo.add(contactWithInfo);
		}
		
		sosUserInfo = MagentoSosUserInfo.getUserInfo(contactWithInfo.getUserId());
		sosContactsInfo = SalesOnSpeedCalls.getListCustomerInfo(sosUserInfo.getSosId(), sosEmail, sosPassowrd);

	}

	@Test
	public void us36001AddSecondNewContactToStyleCoachTest() throws Exception {
		
		magentoToSosSync.validateContactInfoList(contactsInfo, sosContactsInfo);
	}
}
