package com.tests.us6.us6001;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.pages.frontend.checkout.cart.stylistRegistration.StylistRegistrationCartTotalModel;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.datahandlers.stylistRegistration.StylistRegDataGrabber;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.workflows.frontend.stylecoachRegistration.StylecoachRegistrationCartWorkflows;

@WithTag(name = "US6.1 Sc Registration New Customer Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_1.class)
@RunWith(ThucydidesRunner.class)
public class US6001ScRegistrationNewCustomerTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public CustomVerification customVerification;
	@Steps
	public StylecoachRegistrationCartWorkflows stylecoachRegistrationCartWorkflows;

	private CustomerFormModel customerFormData;
	private DateModel customerFormDate = new DateModel();
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	private StylistRegistrationCartTotalModel calculatedTotals;

	@Before
	public void setUp() throws Exception {
		StylistRegDataGrabber.wipe();
		// Generate data for this test run
		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		calculatedTotals = new StylistRegistrationCartTotalModel();
		calculatedTotals.setDelivery("0.00");
		calculatedTotals.setTotalPrice("100.00");
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us6001ScRegistrationNewCustomerTest() {
		headerSteps.navigateToRegisterForm();
		String formCreationDate = stylistRegistrationSteps.fillCreateCustomerForm(customerFormData, customerFormAddress, birthDate.getDate());
		customerFormDate.setDate(formCreationDate);

		stylecoachRegistrationCartWorkflows.setVerifyTotalsDiscount(calculatedTotals, StylistRegDataGrabber.cartTotals);
		stylecoachRegistrationCartWorkflows.verifyTotalsDiscount();

		customVerification.printErrors();

	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
		MongoWriter.saveDateModel(customerFormDate, getClass().getSimpleName());

	}
}
