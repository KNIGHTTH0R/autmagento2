package com.tests.us6.us6001b;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
import com.tools.cartcalculations.stylistRegistration.StylistRegistrationCartCalculator;
import com.tools.constants.ContextConstants;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.DateModel;
import com.tools.data.frontend.StarterSetProductModel;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.StylistRegDataGrabber;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.stylecoachRegistration.AddStarterSetProductsWorkflow;
import com.workflows.frontend.stylecoachRegistration.StarterSetConfirmationWorkflows;
import com.workflows.frontend.stylecoachRegistration.StylecoachRegistrationCartWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US6.1b Sc Registration New Customer Forbidden Country Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_1.class)
@RunWith(SerenityRunner.class)
public class US6001bScRegistrationNewCustForbiddenCountryTest extends BaseTest {

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
	public StylistContextSteps stylistContextSteps;
	@Steps
	public StarterSetSteps starterSetSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public CustomVerification customVerification;
	@Steps
	public AddStarterSetProductsWorkflow addStarterSetProductsWorkflow;
	@Steps
	public StylecoachRegistrationCartWorkflows stylecoachRegistrationCartWorkflows;
	@Steps
	public StarterSetConfirmationWorkflows starterSetConfirmationWorkflows;

	private CustomerFormModel customerFormData;
	private DateModel customerFormDate = new DateModel();
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	//private CreditCardModel creditCardData = new CreditCardModel();
	private String taxClass, shippingValue, voucherValue, voucherCode;

	@Before
	public void setUp() throws Exception {
		DataGrabber.wipe();
		StylistRegDataGrabber.wipe();
		StylistRegistrationCartCalculator.wipe();

		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		customerFormAddress.setCountryName(ContextConstants.NOT_PREFERED_LANGUAGE);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us6" + File.separator + "us6001b.properties");
			prop.load(input);

			taxClass = prop.getProperty("taxClass");
			shippingValue = prop.getProperty("shippingValue");
			voucherValue = prop.getProperty("voucherValue");
			voucherCode = prop.getProperty("voucherCode");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		birthDate.setDate("Feb,1970,12");
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us6001bScRegistrationNewCustForbiddenCountryTest() {
		headerSteps.navigateToRegisterForm();
		String formCreationDate = stylistRegistrationSteps.fillCreateCustomerFormFirstWithForbiddenCountry(
				customerFormData, customerFormAddress, birthDate.getDate());
		customerFormDate.setDate(formCreationDate);

		stylistContextSteps.addStylistReference(customerFormData.getFirstName() + customerFormData.getLastName());

		StarterSetProductModel productData;
		productData = addStarterSetProductsWorkflow.setStarterSetProductToCart(EnvironmentConstants.STARTERSET,
				EnvironmentConstants.STARTERKITPRICE);
		StylistRegistrationCartCalculator.allProductsList.add(productData);

		starterSetSteps.applyVoucher(voucherCode);

		StylistRegistrationCartCalculator.calculateCartAndShippingTotals(taxClass, shippingValue, voucherValue, true);

		starterSetSteps.grabCartTotal(true);
		starterSetSteps.submitStarterSetStep();

		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		// paymentSteps.expandCreditCardForm();
		// paymentSteps.fillCreditCardForm(creditCardData);
		//
		// confirmationSteps.grabConfirmationTotals();
		// confirmationSteps.agreeAndCheckout();

		// if (paymentSteps.isKlarnaAvailable()) {
		// paymentSteps.expandKlarnaForm();
		// paymentSteps.fillKlarnaForm();
		// } else {

//		 paymentSteps.expandCreditCardForm();
//		 paymentSteps.fillCreditCardForm(creditCardData);
		paymentSteps.payWithBankTransfer();
		confirmationSteps.grabConfirmationTotals();
		confirmationSteps.agreeAndCheckout();

		starterSetConfirmationWorkflows.setVerifyConfirmationTotals(DataGrabber.confirmationTotals,
				StylistRegistrationCartCalculator.shippingCalculatedModel);
		starterSetConfirmationWorkflows.verifyConfirmationTotals("CONFIRMATION TOTALS");
		// }

		stylecoachRegistrationCartWorkflows.setVerifyTotalsDiscount(
				StylistRegistrationCartCalculator.cartCalcDetailsModel, StylistRegDataGrabber.cartTotals);
		stylecoachRegistrationCartWorkflows.verifyTotalsDiscount("STARTER SET TOTALS");

		customVerification.printErrors();

	}

	@After
	public void saveData() {
		MongoWriter.saveStarterSetCartCalcDetailsModel(StylistRegistrationCartCalculator.cartCalcDetailsModel,
				getClass().getSimpleName());
		MongoWriter.saveShippingModel(StylistRegistrationCartCalculator.shippingCalculatedModel,
				getClass().getSimpleName());
		MongoWriter.saveCustomerFormModel(customerFormData, getClass().getSimpleName());
		MongoWriter.saveDateModel(customerFormDate, getClass().getSimpleName());
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName());

	}
}
