package com.tests.uss10.us10006;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.external.EmailClientSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.tests.BaseTest;
import com.tools.constants.ConfigConstants;
import com.tools.constants.ContextConstants;
import com.tools.constants.SoapKeys;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;

@WithTag(name = "US10.6 Order for Customer as Party host and Validate Party Wishlist", type = "Scenarios")
@Story(Application.StyleParty.US10_6.class)
@RunWith(SerenityRunner.class)
public class US10006RegisterFromInviteToPartyEmailTest extends BaseTest {

	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;

	private AddressModel addressModel;
	private CustomerFormModel customerData;

	@Before
	public void setUp() throws Exception {

		addressModel = new AddressModel();
		int size = MongoReader.grabCustomerFormModels("US10006CreatePartyWithNewContactHostTest" + SoapKeys.GRAB).size();
		if (size > 0) {
			customerData = MongoReader.grabCustomerFormModels("US10006CreatePartyWithNewContactHostTest" + SoapKeys.GRAB).get(0);
		} else
			System.out.println("The database has no entries");

	}

	@Test
	public void us10006RegisterFromInviteToPartyEmailTest() {

		emailClientSteps.registerFromLink(customerData.getEmailName().replace("@" + ConfigConstants.WEB_MAIL, ""), ContextConstants.PARTY_CREATION_EMAIL_SUBJECT);
		customerRegistrationSteps.fillCreateCustomerForm(customerData, addressModel);
		customerRegistrationSteps.verifyCustomerCreation();
	}

}
