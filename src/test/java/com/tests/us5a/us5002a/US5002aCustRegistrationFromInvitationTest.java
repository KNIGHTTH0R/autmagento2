package com.tests.us5a.us5002a;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.external.faceboook.FacebookInvitationSteps;
import com.steps.external.faceboook.FacebookRegistrationSteps;
import com.steps.external.ospm.OnlineStylePartyManagerSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.profile.ProfileNavSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class US5002aCustRegistrationFromInvitationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ProfileNavSteps profileNavSteps;
	@Steps
	public FacebookInvitationSteps facebookInvitationSteps;
	@Steps
	public FacebookRegistrationSteps facebookLoginSteps;
	@Steps
	OnlineStylePartyManagerSteps fBpermissionSteps;
	@Steps
	public FacebookRegistrationSteps facebookRegistrationSteps;

	private String fbInviteeEmail, fbInviteePass, stylistEmail;
	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	private String message;

	@Before
	public void setUp() throws Exception {

		message = MongoReader.grabStringValue("US5002aInviteFbFriendAppInstalledFbLoggedOutTest").get(0);

		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_05a_FOLDER + File.separator + "us5001a.properties");
			prop.load(input);
			fbInviteeEmail = prop.getProperty("fbInviteeUser");
			fbInviteePass = prop.getProperty("fbInviteePass");
			stylistEmail = prop.getProperty("username");

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
		// Clean DB
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us5002aCustRegistrationFromInvitationTest() throws Exception {
		facebookLoginSteps.loginToFacebook(fbInviteeEmail, fbInviteePass);
		facebookLoginSteps.accesMessagesPage();
		facebookInvitationSteps.selectLatestFBMessage();
		facebookInvitationSteps.verifyInvitationIsReceived(message);
		headerSteps.switchToNewestOpenedTab();
		facebookInvitationSteps.clickOnInvitationLink();
		facebookRegistrationSteps.fillCreateInvitationCustomerForm(dataModel, addressModel, fbInviteeEmail,
				stylistEmail);
		headerSteps.checkSucesfullLoginDE();
	}

}
