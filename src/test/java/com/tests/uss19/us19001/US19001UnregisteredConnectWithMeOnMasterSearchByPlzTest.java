package com.tests.uss19.us19001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

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

@WithTag(name = "US19", type = "frontend")
@Story(Application.KoboCampaign.class)
@RunWith(SerenityRunner.class)
public class US19001UnregisteredConnectWithMeOnMasterSearchByPlzTest extends BaseTest {

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
	public void us19001UnregisteredConnectWithMeOnMasterSearchByPlzTest() {

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