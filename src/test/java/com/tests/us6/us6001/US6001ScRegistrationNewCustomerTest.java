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
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.datahandlers.DataGrabber;
import com.tools.datahandlers.stylistRegistration.StylistRegDataGrabber;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
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
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public CustomVerification customVerification;
	@Steps
	public StylecoachRegistrationCartWorkflows stylecoachRegistrationCartWorkflows;

	private CustomerFormModel customerFormData;
	private DateModel customerFormDate = new DateModel();
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	private StylistRegistrationCartTotalModel calculatedTotals;
	private CreditCardModel creditCardData = new CreditCardModel();

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
		
		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));
		
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		confirmationSteps.agreeAndCheckout();

		stylecoachRegistrationCartWorkflows.setVerifyTotalsDiscount(calculatedTotals, StylistRegDataGrabber.cartTotals);
		stylecoachRegistrationCartWorkflows.verifyTotalsDiscount();

		customVerification.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
		MongoWriter.saveDateModel(customerFormDate, getClass().getSimpleName());
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName());
		MongoWriter.saveAddressModel(customerFormAddress, getClass().getSimpleName());
	}
}
