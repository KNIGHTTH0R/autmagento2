package com.tests.uss41.us41001_StarterKit;

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
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
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

@WithTag(name = "US6.1 Sc Registration New Customer Test ", type = "Scenarios")
@Story(Application.StylecoachRegistration.US6_1.class)
@RunWith(SerenityRunner.class)
public class US41001ScRegistrationNewCustomerTest extends BaseTest {

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
	public StarterSetSteps starterSetSteps;
	@Steps
	public CustomVerification customVerification;
	@Steps
	public StylecoachRegistrationCartWorkflows stylecoachRegistrationCartWorkflows;
	@Steps
	public StylistContextSteps stylistContextSteps;
	@Steps
	public AddStarterSetProductsWorkflow addStarterSetProductsWorkflow;
	@Steps
	public StarterSetConfirmationWorkflows starterSetConfirmationWorkflows;

	private CustomerFormModel customerFormData;
	private DateModel customerFormDate = new DateModel();
	private DateModel birthDate = new DateModel();
	private AddressModel customerFormAddress;
	private CreditCardModel creditCardData = new CreditCardModel();
	private String taxClass, shippingValue, voucherValue, voucherCode;

	@Before
	public void setUp() throws Exception {
		DataGrabber.wipe();
		StylistRegDataGrabber.wipe();
		StylistRegistrationCartCalculator.wipe();

		customerFormData = new CustomerFormModel();
		customerFormAddress = new AddressModel();
		birthDate.setDate("Feb,1970,12");

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us6" + File.separator + "us6001.properties");
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

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us41001ScRegistrationNewCustomerTest() {

		headerSteps.navigateToRegisterForm();

		String formCreationDate = stylistRegistrationSteps.fillCreateCustomerForm(customerFormData, customerFormAddress,
				birthDate.getDate());
		customerFormDate.setDate(formCreationDate);

		stylistContextSteps.addStylistReference(customerFormData.getFirstName() + customerFormData.getLastName());

		StarterSetProductModel productData;

		productData = addStarterSetProductsWorkflow.setStarterSetProductToCart(EnvironmentConstants.STARTERSET,
				EnvironmentConstants.STARTERKITPRICE);
		StylistRegistrationCartCalculator.allProductsList.add(productData);

		starterSetSteps.applyVoucher(voucherCode);

		StylistRegistrationCartCalculator.calculateCartAndShippingTotals(taxClass, shippingValue, voucherValue, false);

		starterSetSteps.grabCartTotal(true);
		starterSetSteps.submitStarterSetStep();

		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		// if (paymentSteps.isKlarnaAvailable()) {
		// paymentSteps.expandKlarnaForm();
		// paymentSteps.fillKlarnaForm();
		// } else {

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);
		// }
		// paymentSteps.payWithBankTransferEs();
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
		MongoWriter.saveAddressModel(customerFormAddress, getClass().getSimpleName());
	}
}
