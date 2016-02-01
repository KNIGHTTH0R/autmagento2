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

import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.DashboardSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.partyHost.HostCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.steps.frontend.reports.JewelryBonusHistorySteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.data.UrlModel;
import com.tools.data.frontend.AddressModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.env.constants.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.workflows.frontend.AddressWorkflows;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

@WithTag(name = "US24.1 Check plz validation on all carts and registration processes", type = "Scenarios")
@Story(Application.PlzValidation.US24_1.class)
@RunWith(ThucydidesParameterizedRunner.class)
@UseTestDataFrom(value = "resources/validPlzTestData.csv")
public class US24001OrderForCustomerPlzValidationTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public HostCartSteps hostCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public JewelryBonusHistorySteps jewelryBonusHistorySteps;
	@Steps
	public DashboardSteps dashboardSteps;
	@Steps
	public CustomVerification customVerifications;

	private String username, password, customerName;

	private CreditCardModel creditCardData = new CreditCardModel();
	private static UrlModel urlModel = new UrlModel();
	private AddressModel addressModel;
	private ProductDetailedModel genProduct1;
	private String plz;

	@Qualifier
	public String getQualifier() {
		return plz;
	}

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();

		addressModel = new AddressModel();
		addressModel.setPostCode(plz);

		genProduct1 = MagentoProductCalls.createZzzProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createJbZzzApiProduct(genProduct1);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10006.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			customerName = prop.getProperty("customerName");

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

		urlModel = MongoReader.grabUrlModels("US24001CreatePartyWithNewContactPlzValidationTest" + plz).get(0);

	}

	@Test
	public void us24001OrderForCustomerPlzValidationTest() {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();

		customerRegistrationSteps.navigate(urlModel.getUrl());
		partyDetailsSteps.orderForCustomer();
		partyDetailsSteps.orderForCustomerFromParty(customerName);
		customerRegistrationSteps.wipeHostCart();
		RegularBasicProductModel productData;

		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		hostCartSteps.clickGoToShipping();

		shippingPartySectionSteps.checkItemNotReceivedYet();

		shippingSteps.clearAndInputNewPostCode(addressModel.getPostCode());
		shippingSteps.clearAndInputNewHomeTown(addressModel.getHomeTown());
		shippingSteps.setSameAsBilling(true);
		shippingSteps.goToPaymentMethod();
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		AddressModel checkoutStepTwoBillAddress = confirmationSteps.grabBillingData();
		AddressModel checkoutStepTwoShipAddress = confirmationSteps.grabSippingData();

		confirmationSteps.agreeAndCheckout();

		AddressWorkflows.setBillingPlz(addressModel.getPostCode(), checkoutStepTwoBillAddress);
		AddressWorkflows.validateBillingPostcode();

		AddressWorkflows.setShippingPlz(addressModel.getPostCode(), checkoutStepTwoShipAddress);
		AddressWorkflows.validateShippingPostcode();

		checkoutValidationSteps.verifySuccessMessage();

		customVerifications.printErrors();

	}

}
