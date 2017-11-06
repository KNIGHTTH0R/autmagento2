package com.tests.uss42.uss42_3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.PippaDb.PippaDBConnection;
import com.steps.external.academy.LoginAcademySteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyCreationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.SingleSignOnSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.constants.UrlConstants;
import com.tools.constants.WebsiteAndStoreViewConstants;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "SSO login from shop / de website", type = "Scenarios")
@RunWith(SerenityRunner.class)
public class US42001SSOCheckSwitchWhenStylistIsChengedInAcademyTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public PartyCreationSteps partyCreationSteps;
	@Steps
	public SingleSignOnSteps singleSignOnSteps;
	@Steps
	CustomVerification customVerification;
	@Steps
	LoginAcademySteps loginAcademySteps;

	private String username, password, customerId, customerContext;

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss42" + File.separator + "us42001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			customerId = prop.getProperty("customerId");
			customerContext = prop.getProperty("context");

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
	public void us42001SSOCheckSwitchWhenStylistIsChengedInAcademyTest() throws SQLException {

		// CASE 1: customer DE preffered website

		System.out.println("CASE 1: customer DE preffered website");

		PippaDBConnection.updateCustomerWebsite(customerId, WebsiteAndStoreViewConstants.DE_WEBSITE);
		PippaDBConnection.updateCustomerStoreView(customerId, WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.DE_STORE_VIEW);

		customerRegistrationSteps.performLoginOnWebsite(username, password, WebsiteAndStoreViewConstants.DE_WEBSITE);
		singleSignOnSteps.validateShopLoginWebsiteAndStoreView(WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.DE_STORE_VIEW, customerContext);
		singleSignOnSteps.validateLoggedInAcademySameTab();
		loginAcademySteps.performLogoutFromAcademy();
		singleSignOnSteps.validateLoggedOutFromAcademy();
		loginAcademySteps.performAcademyLogin("sso_customer1@evozon.com", password);
		singleSignOnSteps.validateLoggedInAcademy();
		
		singleSignOnSteps.clickOnShopLogo();
		// validate all website
		singleSignOnSteps.validateShopLoginOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.DE_STORE_VIEW,"DOMINIQUE");
		singleSignOnSteps.validateShopLoginOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.DE_STORE_VIEW,"DOMINIQUE");
		/*customerRegistrationSteps.performLogoutFromShop(WebsiteAndStoreViewConstants.EN_WEBSITE);
		singleSignOnSteps.validateShopLogoutWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.DE_STORE_VIEW, customerContext);
		singleSignOnSteps.validateLoggedOutAlreadyFromAcademy();

		singleSignOnSteps.validateShopLogoutOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.ES_WEBSITE,
				WebsiteAndStoreViewConstants.DE_STORE_VIEW);
		singleSignOnSteps.validateShopLogoutOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.DE_STORE_VIEW);
*/
		// CASE 2: customer EN preffered website System.out.println(

		customVerification.printErrors();
	}

	@After
	public void saveData() {

	}

}
