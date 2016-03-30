package com.tests.uss20.us20001;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.tests.BaseTest;
import com.tools.data.StylistDataModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US20.1 Verify new SC and updated SC details in Commission", type = "Scenarios")
@Story(Application.StylecoachInfo.US20_1.class)
@RunWith(SerenityRunner.class)
public class US20001StyleCoachRegistrationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;

	private CreditCardModel creditCardData = new CreditCardModel();
	public CustomerFormModel customerFormData;
	public DateModel customerFormDate = new DateModel();
	public DateModel birthDate = new DateModel();
	public AddressModel customerFormAddress;
	public StylistDataModel validationModel;

	@Before
	public void setUp() throws Exception {
		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us20001StyleCoachRegistrationTest() {
		headerSteps.navigateToRegisterForm();
		String formCreationDate = stylistRegistrationSteps.fillCreateCustomerFormPayWithVisa(customerFormData, customerFormAddress, birthDate.getDate());
		customerFormDate.setDate(formCreationDate);

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
		MongoWriter.saveDateModel(customerFormDate, getClass().getSimpleName());

	}
}
