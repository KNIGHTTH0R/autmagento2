package com.tests.us5b;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
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
import com.steps.frontend.KoboValidationSteps;
import com.steps.frontend.profile.ProfileNavSteps;
import com.steps.frontend.profile.ProfileSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.ContextConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FieldGenerators;
import com.tools.utils.FieldGenerators.Mode;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;



@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class US5001bShareOnlineBoutiqueOnFacebookTest extends BaseTest{

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
	public FacebookInvitationSteps shareFacebookSteps;
	@Steps
	public FacebookRegistrationSteps facebookLoginSteps;
	@Steps
	OnlineStylePartyManagerSteps fBpermissionSteps;
	@Steps
	public FacebookRegistrationSteps facebookRegistrationSteps;
	@Steps
	public KoboValidationSteps koboValidationSteps;
	
	private String username, password,fbEmail,fbPass, appID,inviteeName,fbUserName,userKoboCode  ;
	private String message,userStylistContext;
	
	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_05a_FOLDER + File.separator + "us5001a.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			userKoboCode=prop.getProperty("userKoboCode");
			userStylistContext=prop.getProperty("userStylistContext");
			fbEmail = prop.getProperty("fbUser");
			fbPass = prop.getProperty("fbPass");
			fbUserName=prop.getProperty("fbUserName");
			appID = prop.getProperty("appID");
			inviteeName = prop.getProperty("inviteeName");
			

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

		message=FieldGenerators.generateRandomString(10, Mode.ALPHANUMERIC)+" share5001b";
		System.out.println("message: "+message);
		// Clean DB
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}
	
	
	@Test
	public void us5001bShareOnlineBoutiqueOnFacebookTest() throws Exception {
		frontEndSteps.performLogin("mihaialexandrubarta@gmail.com", password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		
		
		headerSteps.switchToDeStoreView();
		headerSteps.goToLounge();
		headerSteps.clickOnShareOnlineBootique();
		facebookLoginSteps.loginToFacebookPippa(fbEmail, fbPass);
		shareFacebookSteps.insertShareMessage(message);
		shareFacebookSteps.sendSharePostAndSwitchPage("Stylist Lounge");
		//
		facebookLoginSteps.accessFacebookPage("emilian.mihai.712");
		facebookLoginSteps.accessFacebookPage(fbUserName);
		shareFacebookSteps.verifySharedMessage(message);
		headerSteps.switchToNewestOpenedTab();
	///	koboValidationSteps.verifyKoboCodeInModal(userKoboCode);
		headerSteps.verifyWebsiteAndStylistContext("de",userStylistContext);
	}
	
	@After
	public void saveData() {
		MongoWriter.saveStringValue(message,getClass().getSimpleName());
		
	}
}
