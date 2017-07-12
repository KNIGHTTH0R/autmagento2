package com.tests.us5.us5002;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.steps.external.faceboook.FacebookRegistrationSteps;
import com.steps.external.ospm.OnlineStylePartyManagerSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyCreationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.requirements.Application;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "USS37", type = "facebook login- frontend")
@Story(Application.OnlineStylePartyManager.class)
@RunWith(SerenityRunner.class)
public class US5002FacebookRegUserFBLoggedOutAppInstaledTest extends BaseTest{

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
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
	CustomVerification customVerification;

	private String fbEmail, fbPass, fbName;
	@Steps
	public FacebookRegistrationSteps facebookRegistrationSteps;

	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	private CustomerFormModel dataModel;
	private AddressModel addressModel;
	
	@Before
	public void setUp() throws Exception {
		
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_05_FOLDER + File.separator + "us5001.properties");
			prop.load(input);
			fbEmail = prop.getProperty("fbUser");
			fbPass = prop.getProperty("fbPass");
			fbName = prop.getProperty("name");
			
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
	public void us5002FacebookRegUserFBLoggedOutAppInstaledTest() throws Exception {
		//use data from US5001 suite
		facebookRegistrationSteps.goToFacebookRegistration();
		facebookRegistrationSteps.loginToFacebookPippaAndSwitchPage(fbEmail, fbPass);
		facebookRegistrationSteps.verifyFbUserInfo(fbName,fbEmail);
		facebookRegistrationSteps.fillCreateCustomerByFacebookForm(dataModel, addressModel,fbPass);
		headerSteps.checkSucesfullLoginDE();
		customVerification.printErrors();

	}
}
