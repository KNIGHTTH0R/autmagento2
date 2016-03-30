package com.tests.uss10.uss10008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.tests.BaseTest;
import com.tools.data.UrlModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US10.8 Check virgin party performance and bonuses", type = "Scenarios")
@Story(Application.PartyPerformance.US10_8.class)
@RunWith(SerenityRunner.class)
public class US10008CreatePartyWithNewContactHostTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CreateNewContactSteps createNewContactSteps;
	@Steps
	public HeaderSteps headerSteps;

	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public PartyCreationSteps partyCreationSteps;

	private static UrlModel urlModel = new UrlModel();
	private String username, password;
	private CustomerFormModel customerData;
	private CustomerFormModel inviteData;
	private AddressModel addressData;

	@Before
	public void setUp() throws Exception {

		customerData = new CustomerFormModel();
		inviteData = new CustomerFormModel();
		addressData = new AddressModel();

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

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

		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us10008CreatePartyWithNewContactHostTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToCreatePartyWithNewContactPage();
		createNewContactSteps.fillCreateNewContact(customerData, addressData);
		urlModel.setUrl(partyCreationSteps.fillPartyDetailsForNewCustomerHost());
		partyDetailsSteps.verifyPlannedPartyAvailableActions();
		partyDetailsSteps.sendInvitationToGest(inviteData);
		partyDetailsSteps.verifyThatGuestIsInvited(inviteData.getFirstName());

	}

	@After
	public void saveData() {
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName());
	}
}
