package com.tests.uss24;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import net.thucydides.junit.runners.ThucydidesParameterizedRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.steps.frontend.registration.party.CreateNewContactSteps;
import com.steps.frontend.reports.JewelryBonusHistorySteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.partyHost.HostCartCalculator;
import com.tools.datahandlers.partyHost.HostDataGrabber;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.AddressWorkflows;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;
import com.workflows.frontend.partyHost.HostCartValidationWorkflows;

@WithTag(name = "US24.1 Check plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(ThucydidesParameterizedRunner.class)
@UseTestDataFrom(value = "resources/validPlzTestData.csv")
public class US24001PlaceCustomerOrderPlzValidationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public LoungeSteps loungeSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public CreateNewContactSteps createNewContactSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public OrderForCustomerCartSteps orderForCustomerCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddProductsForCustomerWorkflow addProductsForCustomerWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public HostCartValidationWorkflows hostCartValidationWorkflows;
	@Steps
	public JewelryBonusHistorySteps jewelryBonusHistorySteps;
	@Steps
	public DashboardSteps dashboardSteps;
	@Steps
	public CustomVerification customVerifications;

	private String username, password;
	private CreditCardModel creditCardData = new CreditCardModel();
	private CustomerFormModel customerData;
	private AddressModel addressData;
	private ProductDetailedModel genProduct1;
	private String plz;

	@Qualifier
	public String getQualifier() {
		return plz;
	}

	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();

		customerData = new CustomerFormModel();
		addressData = new AddressModel();
		addressData.setPostCode(plz);

		genProduct1 = ApiCalls.createZzzProductModel();
		genProduct1.setPrice("89.00");
		genProduct1.setIp("75");
		ApiCalls.createJbZzzApiProduct(genProduct1);
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss15" + File.separator + "us15004.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

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

	}

	@Test
	public void us24001PlaceCustomerOrderPlzValidationTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();

		loungeSteps.orderForNewCustomer();
		createNewContactSteps.fillCreateNewContactDirectly(customerData, addressData);
		customerRegistrationSteps.wipeHostCart();

		addProductsForCustomerWorkflow.setHostProductToCart(genProduct1, "1", "0");

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		orderForCustomerCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();
		shippingPartySectionSteps.checkItemNotReceivedYet();
		shippingSteps.clearAndInputNewPostCode(addressData.getPostCode());
		shippingSteps.clearAndInputNewHomeTown(addressData.getHomeTown());
		shippingSteps.setSameAsBilling(true);
		shippingSteps.goToPaymentMethod();
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		AddressModel checkoutStepTwoBillAddress = confirmationSteps.grabBillingData();
		AddressModel checkoutStepTwoShipAddress = confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		AddressWorkflows.setBillingPlz(addressData.getPostCode(), checkoutStepTwoBillAddress);
		AddressWorkflows.validateBillingPostcode();

		AddressWorkflows.setShippingPlz(addressData.getPostCode(), checkoutStepTwoShipAddress);
		AddressWorkflows.validateShippingPostcode();

		checkoutValidationSteps.verifySuccessMessage();

		customVerifications.printErrors();

	}

}
