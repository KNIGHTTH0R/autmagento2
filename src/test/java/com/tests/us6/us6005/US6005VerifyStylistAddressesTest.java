package com.tests.us6.us6005;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.tests.BaseTest;
import com.tools.constants.Credentials;
import com.tools.data.frontend.AddressModel;
import com.tools.data.geolocation.CoordinatesModel;
import com.tools.data.soap.DBStylistModel;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.RandomAddress;

@WithTag(name = "US6.5 Stylist registration with redirects From Step 5 back to Step 1 and Step 3 ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_5.class)
@RunWith(SerenityRunner.class)
public class US6005VerifyStylistAddressesTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;

	public String stylistEmail;

	public AddressModel newShippingAddress;
	public AddressModel newBillingAddress;
	CoordinatesModel coordinatesModel = new CoordinatesModel();
	RandomAddress randomAddress;
	List<DBStylistModel> compatibleStylistList = new ArrayList<DBStylistModel>();

	@Before
	public void setUp() throws Exception {

		stylistEmail = MongoReader.grabCustomerFormModels("US6005ScRegistrationNewCustomerTest").get(0).getEmailName();
		newShippingAddress = MongoReader.grabAddressModels("US6005ScRegistrationNewCustomerTest" + "shipping").get(0);
		newBillingAddress = MongoReader.grabAddressModels("US6005ScRegistrationNewCustomerTest" + "billing").get(0);
	}

	@Test
	public void us6005VerifyStylistAddressesTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistEmail);
		backEndSteps.openCustomerDetails(stylistEmail);
		backEndSteps.clickOnAddressesTab();
		backEndSteps.verifyThatAddressExist(newShippingAddress);
		backEndSteps.verifyThatAddressExist(newBillingAddress);

	}
}
