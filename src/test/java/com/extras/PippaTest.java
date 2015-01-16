package com.extras;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.steps.backend.BackEndSteps;
import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tools.Constants;
import com.tools.data.AddressModel;
import com.tools.data.CustomerFormModel;
import com.tools.data.StylistDataModel;
import com.tools.requirements.Application;

@Story(Application.Stylist.CreateColaborator.class)
@RunWith(ThucydidesRunner.class)
public class PippaTest {

	@Managed(uniqueSession = false)
	public WebDriver webdriver;

	@ManagedPages(defaultUrl = Constants.BASE_URL_BE)
	public Pages pages;

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public EmailClientSteps emailClientSteps;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public StylistDataModel validationModel;

	@Before
	public void setUp() throws Exception {

		// Generate data for this test run
		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();

		System.out.println("FN: " + dataModel.getFirstName());
		System.out.println("LN: " + dataModel.getLastName());
		System.out.println("EMAIL: " + dataModel.getEmailName());
		System.out.println("PASS: " + dataModel.getPassword());
	}

	/**
	 * BackEnd steps in this test
	 */
	@Test
	public void checkStylistProperties() {
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		// backEndSteps.dismissPopUp();
		backEndSteps.redirectToManageCustomers();
		backEndSteps.searchForEmail(Constants.BE_STYLIST);
		backEndSteps.openCustomerDetails(Constants.BE_STYLIST);
		backEndSteps.clickOnLeadSettings();
		validationModel = backEndSteps.grabLeadSettingsData();

	}

	/**
	 * FrontEnd steps in this test
	 * 
	 * @throws Exception
	 */
	@Test
	public void createFECustomerTest() {

		frontEndSteps.fillCreateCustomerForm(dataModel, addressModel);
		frontEndSteps.verifyCustomerCreation();
	}

	@Test
	public void confirmEmail() {
		// Confirm Email
		emailClientSteps.openMailinator();
		emailClientSteps.grabEmail(dataModel.getEmailName().replace(
				"@" + Constants.WEB_MAIL, ""));
	}

	@Test
	public void checkBECustomerActivation() {
		backEndSteps.performAdminLogin(Constants.BE_USER, Constants.BE_PASS);
		// backEndSteps.dismissPopUp();
		backEndSteps.redirectToManageCustomers();
		backEndSteps.searchForEmail("oP2Mc4Vk@mailinator.com");
		backEndSteps.openCustomerDetails("oP2Mc4Vk@mailinator.com");
		backEndSteps.extractEmailConfirmationStatus();
	}

}