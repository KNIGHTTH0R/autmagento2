package com.tests.uss24;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import net.thucydides.junit.runners.ThucydidesParameterizedRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.frontend.HeaderSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;

@WithTag(name = "US24.1 Check plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(ThucydidesParameterizedRunner.class)
@UseTestDataFrom(value = "resources/validPlzTestData.csv")
public class US24001StylistRegistrationPlzValidationTest extends BaseTest {

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
	private CustomerFormModel customerFormData;
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	private String plz;

	@Qualifier
	public String getQualifier() {
		return plz;
	}

	@Before
	public void setUp() throws Exception {

		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		customerFormAddress.setPostCode(plz);
		birthDate.setDate("Feb,1970,12");
	}

	@Test
	public void us24001StylistRegistrationPlzValidationTest() {
		headerSteps.navigateToRegisterForm();
		stylistRegistrationSteps.fillCreateCustomerForm(customerFormData, customerFormAddress, birthDate.getDate());

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
	}
}
