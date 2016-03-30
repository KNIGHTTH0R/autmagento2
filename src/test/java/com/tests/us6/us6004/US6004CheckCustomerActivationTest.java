package com.tests.us6.us6004;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.backend.BackEndSteps;
import com.steps.backend.OrdersSteps;
import com.steps.backend.validations.StylistValidationSteps;
import com.steps.external.EmailClientSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.backend.RegistrationActivationDateModel;
import com.tools.data.backend.StylistPropertiesModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.constants.Credentials;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.PrintUtils;
import com.workflows.backend.CustomerAndStylistRegistrationWorkflows;

@WithTag(name = "US6.4 Stylist Registration abandoned before payment", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_4.class)
@RunWith(SerenityRunner.class)
public class US6004CheckCustomerActivationTest extends BaseTest {

	@Steps
	public BackEndSteps backEndSteps;
	@Steps
	public OrdersSteps ordersSteps;
	@Steps
	public EmailClientSteps emailClientSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public StylistValidationSteps stylistValidationSteps;
	@Steps
	public CustomerAndStylistRegistrationWorkflows customerAndStylistRegistrationWorkflows;

	private StylistPropertiesModel expectedStylistData = new StylistPropertiesModel();
	private CustomerFormModel stylistRegistrationData = new CustomerFormModel("");
	private RegistrationActivationDateModel datesExpected = new RegistrationActivationDateModel();
	private String formCreationDate;

	@Before
	public void setUp() throws Exception {

		formCreationDate = MongoReader.grabDateModels("US6004StylistRegistrationStopToStep4Test").get(0).getDate();
		int size = MongoReader.grabCustomerFormModels("US6004StylistRegistrationStopToStep4Test").size();
		if (size > 0) {
			stylistRegistrationData = MongoReader.grabCustomerFormModels("US6004StylistRegistrationStopToStep4Test").get(0);
		} else
			System.out.println("The database has no entries");

		expectedStylistData = new StylistPropertiesModel(ConfigConstants.CONFIRMED, ConfigConstants.JEWELRY_INITIAL_VALUE, ConfigConstants.GENERAL);
		datesExpected = new RegistrationActivationDateModel(formCreationDate, formCreationDate);
	}

	@Test
	public void us6004CheckCustomerActivationTest() {

		backEndSteps.performAdminLogin(Credentials.BE_USER, Credentials.BE_PASS);
		backEndSteps.clickOnCustomers();
		backEndSteps.searchForEmail(stylistRegistrationData.getEmailName());
		backEndSteps.openCustomerDetails(stylistRegistrationData.getEmailName());

		StylistPropertiesModel grabStylistData = backEndSteps.grabCustomerConfiguration();
		RegistrationActivationDateModel grabDatesModel = backEndSteps.grabStylistRegistrationAndConfirmationDates();

		customerAndStylistRegistrationWorkflows.setValidateStylistProperties(grabStylistData, expectedStylistData);
		customerAndStylistRegistrationWorkflows.validateStylistProperties("BEFORE CONFIRMATION LINK");
		PrintUtils.printStylistPropertiesModel(grabStylistData);
		PrintUtils.printStylistPropertiesModel(expectedStylistData);
		
		customerAndStylistRegistrationWorkflows.setValidateStylistDates(grabDatesModel, datesExpected);
		customerAndStylistRegistrationWorkflows.validateCustomerRegistrationDate("VALIDATE CUSTOMER ACCOUNT REGISTRATION DATE");

		customVerifications.printErrors();
	}

}
