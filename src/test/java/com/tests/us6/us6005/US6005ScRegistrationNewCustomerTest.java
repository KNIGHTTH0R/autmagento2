package com.tests.us6.us6005;

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
import com.steps.frontend.StarterSetSteps;
import com.steps.frontend.StylistCampaignSteps;
import com.steps.frontend.StylistContextSteps;
import com.steps.frontend.StylistRegistrationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.stylecoachRegistration.StylecoachRegistrationCartWorkflows;

@WithTag(name = "US6.5 Stylist registration with redirects From Step 5 back to Step 1 and Step 3 ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_5.class)
@RunWith(SerenityRunner.class)
public class US6005ScRegistrationNewCustomerTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public StylistCampaignSteps stylistCampaignSteps;
	@Steps
	public StylistRegistrationSteps stylistRegistrationSteps;
	@Steps
	public StarterSetSteps starterSetSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public StylistContextSteps stylistContextSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public CustomVerification customVerification;
	@Steps
	public StylecoachRegistrationCartWorkflows stylecoachRegistrationCartWorkflows;

	private CustomerFormModel customerFormData;
	private DateModel customerFormDate;
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	private AddressModel newShippingAddress;
	private AddressModel newBillingAddress;
	private CreditCardModel creditCardData;

	@Before
	public void setUp() throws Exception {

		customerFormDate = new DateModel();
		creditCardData = new CreditCardModel();
		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		newShippingAddress = new AddressModel();
		newShippingAddress.setPostCode("55555");
		newBillingAddress = new AddressModel();
		newBillingAddress.setPostCode("66666");
		birthDate.setDate("Feb,1970,12");

		MongoConnector.cleanCollection(getClass().getSimpleName());
		MongoConnector.cleanCollection(getClass().getSimpleName() + "shipping");
		MongoConnector.cleanCollection(getClass().getSimpleName() + "billing");
	}

	@Test
	public void us6005ScRegistrationNewCustomerTest() {
		headerSteps.navigateToRegisterForm();
		String formCreationDate = stylistRegistrationSteps.fillCreateCustomerForm(customerFormData, customerFormAddress, birthDate.getDate());
		customerFormDate.setDate(formCreationDate);

		stylistContextSteps.addStylistReference(customerFormData.getFirstName() + customerFormData.getLastName());
		starterSetSteps.selectStarterKit("input#kit_2941");
		starterSetSteps.grabCartTotal(false);
		starterSetSteps.submitStarterSetStep();

		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.clickOnChangeShippingAddress();
		starterSetSteps.setSameAsBilling(false);
		starterSetSteps.fillNewAddressForShipping(newShippingAddress);
		starterSetSteps.goToPaymentMethod();
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.clickOnChangeBillingAddress();
		stylistRegistrationSteps.updateStylistData(customerFormData, newBillingAddress);
		stylistContextSteps.submitContextStep();
		starterSetSteps.submitStarterSetStep();
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();

		customVerification.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
		MongoWriter.saveAddressModel(newShippingAddress, getClass().getSimpleName() + "shipping");
		MongoWriter.saveAddressModel(newBillingAddress, getClass().getSimpleName() + "billing");
	}
}
