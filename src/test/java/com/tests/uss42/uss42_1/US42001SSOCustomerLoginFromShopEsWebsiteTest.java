package com.tests.uss42.uss42_1;

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
public class US42001SSOCustomerLoginFromShopEsWebsiteTest extends BaseTest {

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
	public void us42001SSOLoginFromShopEsWebsiteTest() throws SQLException {

		// CASE 1: customer DE preferred website

		System.out.println("CASE 1: customer ES preffered website");

		PippaDBConnection.updateCustomerWebsite(customerId, WebsiteAndStoreViewConstants.ES_WEBSITE);
		PippaDBConnection.updateCustomerStoreView(customerId, WebsiteAndStoreViewConstants.ES_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);

		customerRegistrationSteps.performLoginOnWebsite(username, password, WebsiteAndStoreViewConstants.ES_WEBSITE);
		
		singleSignOnSteps.validateShopLoginWebsiteAndStoreView(WebsiteAndStoreViewConstants.ES_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW, customerContext);
		singleSignOnSteps.validateLoggedInAlreadyInAcademy();
		// validate all website
		singleSignOnSteps.validateShopLoginOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);
		singleSignOnSteps.validateShopLoginOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);
		customerRegistrationSteps.performLogoutFromShop(WebsiteAndStoreViewConstants.EN_WEBSITE);
		singleSignOnSteps.validateShopLogoutWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW, customerContext);
		singleSignOnSteps.validateLoggedOutAlreadyFromAcademy();

		singleSignOnSteps.validateShopLogoutOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);
		singleSignOnSteps.validateShopLogoutOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.ES_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);

		// CASE 2: customer EN preferred website System.out.println(

		System.out.println("CASE 2: customer EN preffered website");

		PippaDBConnection.updateCustomerWebsite(customerId, WebsiteAndStoreViewConstants.EN_WEBSITE);
		PippaDBConnection.updateCustomerStoreView(customerId, WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);

		customerRegistrationSteps.performLoginOnWebsite(username, password, WebsiteAndStoreViewConstants.ES_WEBSITE);
		singleSignOnSteps.validateShopLoginWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW, customerContext);
		singleSignOnSteps.validateLoggedInAlreadyInAcademy();
		// validate all website
		singleSignOnSteps.validateShopLoginOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);
		singleSignOnSteps.validateShopLoginOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);

		customerRegistrationSteps.performLogoutFromShop(WebsiteAndStoreViewConstants.ES_WEBSITE);
		singleSignOnSteps.validateShopLogoutWebsiteAndStoreView(WebsiteAndStoreViewConstants.ES_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW, customerContext);
		singleSignOnSteps.validateLoggedOutAlreadyFromAcademy();

		singleSignOnSteps.validateShopLogoutOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);
		singleSignOnSteps.validateShopLogoutOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);

		// CASE 3: customer ES preferred website

		System.out.println("CASE 3: customer DE preffered website ");
		PippaDBConnection.updateCustomerWebsite(customerId, WebsiteAndStoreViewConstants.DE_WEBSITE);
		PippaDBConnection.updateCustomerStoreView(customerId, WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);

		customerRegistrationSteps.performLoginOnWebsite(username, password, WebsiteAndStoreViewConstants.ES_WEBSITE);
		singleSignOnSteps.validateShopLoginWebsiteAndStoreView(WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW, customerContext);
		singleSignOnSteps.validateLoggedInAlreadyInAcademy();
		// validate all website
		singleSignOnSteps.validateShopLoginOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.ES_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);
		singleSignOnSteps.validateShopLoginOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);

		customerRegistrationSteps.performLogoutFromShop(WebsiteAndStoreViewConstants.EN_WEBSITE);
		singleSignOnSteps.validateShopLogoutWebsiteAndStoreView(WebsiteAndStoreViewConstants.EN_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW, customerContext);
		singleSignOnSteps.validateLoggedOutAlreadyFromAcademy();

		singleSignOnSteps.validateShopLogoutOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.ES_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);
		singleSignOnSteps.validateShopLogoutOtherWebsiteAndStoreView(WebsiteAndStoreViewConstants.DE_WEBSITE,
				WebsiteAndStoreViewConstants.ES_STORE_VIEW);

		customVerification.printErrors();
	}

	@After
	public void saveData() {

	}

}
