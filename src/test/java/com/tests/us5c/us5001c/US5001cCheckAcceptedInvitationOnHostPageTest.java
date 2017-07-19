package com.tests.us5c.us5001c;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.faceboook.FacebookRegistrationSteps;
import com.steps.external.ospm.OnlineStylePartyGuestSteps;
import com.steps.external.ospm.OnlineStylePartyManagerSteps;
import com.steps.external.ospm.OnlineStylePartyStylistSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.MyContactsListSteps;
import com.steps.frontend.PartyCreationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.OnlineStylePartyModel;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US5001cCheckAcceptedInvitationOnHostPageTest extends BaseTest {
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyCreationSteps partyCreationSteps;
	@Steps
	public FacebookRegistrationSteps facebookLoginSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	OnlineStylePartyManagerSteps fBpermissionSteps;
	@Steps
	OnlineStylePartyGuestSteps onlineStylePartyGuestSteps;
	@Steps
	public MyContactsListSteps myContactsListSteps;
	
	@Steps
	OnlineStylePartyStylistSteps onlineStylePartyStylistSteps;
	@Steps
	CustomVerification customVerification;
	@Steps
	public FacebookRegistrationSteps facebookRegistrationSteps;
	private static OnlineStylePartyModel ospModel = new OnlineStylePartyModel();
	
	private String fbUser, fbPass, appID,username,password;
	private String email, firstName, lastName, country;
	private UrlModel urlModel;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;
		urlModel = MongoReader.grabUrlModels("US5001cCreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);
		System.out.println("urlModel "+urlModel.getUrl());
		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_05c_FOLDER + File.separator + "us5001c.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			email = prop.getProperty("fbInviteeUser");
			fbPass = prop.getProperty("fbInviteePass");
			appID = prop.getProperty("appID");
			country = "Germany";
			firstName = "Qaius";
			lastName = "Bravius";
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Test
	public void us5001cPartyGuestAcceptInvitationUsingFBDataTest() throws Exception {
		customerRegistrationSteps.performLogin(username, password);
		
		customerRegistrationSteps.navigate(urlModel.getUrl());
		ospModel=partyCreationSteps.grabOspModel();
		
		headerSteps.openNewTab();
		headerSteps.switchToNewestOpenedTab();
		onlineStylePartyStylistSteps.navigateToHostPage(ospModel.getHostPageUrl());
		onlineStylePartyStylistSteps.loginIntoHostAccount(ospModel.getHostPassword());
		onlineStylePartyStylistSteps.verifyIfInvitedGuestAppersInList("Qaius B");
		
		headerSteps.switchBackToPreviousTab();
		headerSteps.goToLounge();
		loungeSteps.goToContactsList();
		myContactsListSteps.verifyUnicAndOpenContactDetails(firstName,lastName);
		customVerification.printErrors();
	}
}
