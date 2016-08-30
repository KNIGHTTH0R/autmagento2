package com.tests.uss10.us10006;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.profile.ProfileNavSteps;
import com.steps.frontend.reports.StylistsCustomerOrdersReportSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
@WithTag(name = "US10.6 Order for Customer as Party host and Validate Party Wishlist", type = "Scenarios")
@Story(Application.StyleParty.US10_6.class)
@RunWith(SerenityRunner.class)

public class US10006InviteFriendsToPartyTest extends BaseTest{
	
	@Steps
	public StylistsCustomerOrdersReportSteps stylistsCustomerOrdersReportSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ProfileNavSteps profilenavSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	
	private CustomerFormModel customerData;
	private CustomerFormModel invitedContactData;

	@Before
	public void setUp() throws Exception {
	
		invitedContactData = new CustomerFormModel();
		int size = MongoReader.grabCustomerFormModels("US10006CreatePartyWithNewContactHostTest" + SoapKeys.GRAB).size();
		if (size > 0) {
			customerData = MongoReader.grabCustomerFormModels("US10006CreatePartyWithNewContactHostTest" + SoapKeys.GRAB).get(0);
		} else
			System.out.println("The database has no entries");
		
		MongoConnector.cleanCollection(getClass().getSimpleName()+ SoapKeys.GRAB);

	}
	
	@Test
	public void us10006InviteFriendsToPartyTest() throws IOException {

		frontEndSteps.performLogin(customerData.getEmailName(), customerData.getPassword());
		if (!headerSteps.succesfullLogin()) {

			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();
		profilenavSteps.selectMenu(ContextConstants.MEINE_PARTIES);
		profilenavSteps.clickStylePartyDetailsButton();
//		profilenavSteps.inviteGuestsToPartyButton();	
		partyDetailsSteps.sendInvitationToGest(invitedContactData);
		
		
	}
	
	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(invitedContactData, getClass().getSimpleName()+ SoapKeys.GRAB);
	}

	
}
