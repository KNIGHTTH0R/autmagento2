package com.tests.uss10.us10006;

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
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.PartyCreationSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.UpdatePartySteps;
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.tests.BaseTest;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
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
public class US10006CreatePartyWithNewContactHostTest extends BaseTest {

	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public UpdatePartySteps updatePartySteps;
	@Steps
	public PartyCreationSteps partyCreationSteps;
	@Steps
	public CreateNewContactSteps createNewContactSteps;

	private static UrlModel urlModel = new UrlModel();
	private static DateModel dateModel = new DateModel();
	private String username, password;
	private String customerEmail, customerName;

	private CustomerFormModel customerData;
	private AddressModel addressData;

	@Before
	public void setUp() throws Exception {

		customerData = new CustomerFormModel();
		addressData = new AddressModel();

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10006.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			customerEmail = prop.getProperty("customerUsername");
			customerName = prop.getProperty("customerName");

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

		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}

	@Test
	public void us10006CreatePartyWithNewContactHostTest() {

		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToCreatePartyWithNewContactPage();
		createNewContactSteps.fillCreateNewContact(customerData, addressData);
		urlModel.setUrl(partyCreationSteps.fillPartyDetailsForNewCustomerHost());
		dateModel.setDate(String.valueOf(System.currentTimeMillis()));
		partyDetailsSteps.verifyPlannedPartyAvailableActions();
		partyDetailsSteps.sendInvitationToGest(customerName, customerEmail);
		partyDetailsSteps.verifyThatGuestIsInvited(customerName);
		updatePartySteps.updatePartyDateAndHour();

	}

	@After
	public void saveData() {

		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveDateModel(dateModel, getClass().getSimpleName() + SoapKeys.GRAB);
		MongoWriter.saveCustomerFormModel(customerData, getClass().getSimpleName()+ SoapKeys.GRAB);
		
	
	}
}