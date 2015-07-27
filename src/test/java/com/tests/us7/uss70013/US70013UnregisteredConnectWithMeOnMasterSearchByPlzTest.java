package com.tests.us7.uss70013;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.pages.frontend.registration.connectWithMe.ConnectWithMeAllocationPage.StyleMode;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.registration.connectWithMe.ConnectWithMeRegistrationSteps;
import com.tests.BaseTest;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US7", type = "frontend")
@Story(Application.KoboCampaign.class)
@RunWith(ThucydidesRunner.class)
public class US70013UnregisteredConnectWithMeOnMasterSearchByPlzTest extends BaseTest {

	@Steps
	public ConnectWithMeRegistrationSteps connectWithMeRegistrationSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HomeSteps homeSteps;

	public CustomerFormModel dataModel;
	public AddressModel addressModel;
	public StylistDataModel validationModel;

	@Before
	public void setUp() throws Exception {

		dataModel = new CustomerFormModel();
		addressModel = new AddressModel();
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us70013ConnectWithMeRegistrationTest() {

		connectWithMeRegistrationSteps.navigateToConnectWithMeUnderMasterShop();
		connectWithMeRegistrationSteps.fillConnectWithMeForm(dataModel, addressModel);
		connectWithMeRegistrationSteps.selectStylistOption(StyleMode.DefaultStylist, "", "", addressModel);
		connectWithMeRegistrationSteps.submitStylistSelection();

		connectWithMeRegistrationSteps.verifySuccessLink();
		connectWithMeRegistrationSteps.verifyConnectWithMeRegistrationSuccesMessage();

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(dataModel, getClass().getSimpleName());
	}

}