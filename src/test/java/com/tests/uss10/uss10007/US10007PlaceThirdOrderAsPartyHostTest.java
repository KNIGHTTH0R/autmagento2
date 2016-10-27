package com.tests.uss10.uss10007;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
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
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.PartyBonusCalculationModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;

@WithTag(name = "US10.7 Check party and follow up party performance and bonuses", type = "Scenarios")
@Story(Application.PartyPerformance.US10_7.class)
@RunWith(SerenityRunner.class)
public class US10007PlaceThirdOrderAsPartyHostTest extends BaseTest {

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

	private String username, password, customerName;

	private CreditCardModel creditCardData = new CreditCardModel();
	private PartyBonusCalculationModel partyBonusCalculationModel = new PartyBonusCalculationModel();
	private static UrlModel urlModel = new UrlModel();
	private ProductDetailedModel genProduct1;

	@Before
	public void setUp() throws Exception {
		RegularUserDataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createZzzProductModel();
		genProduct1.setPrice("389.00");
		MagentoProductCalls.createJbZzzApiProduct(genProduct1);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss10" + File.separator + "us10001.properties");
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

		urlModel = MongoReader.grabUrlModels("US10007CreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);

		MongoConnector.cleanCollection(getClass().getSimpleName());

	}

	@Test
	public void us10007PlaceThirdOrderAsPartyHostTest() {
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

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		RegularUserDataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		partyBonusCalculationModel.setTotal(confirmationSteps.grabConfirmationTotals().getSubTotal());
		partyBonusCalculationModel.setIp(genProduct1.getIp());
		partyBonusCalculationModel.setPercent("100");

		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();

	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModel, getClass().getSimpleName());
		MongoWriter.savePartyBonusCalculationModel(partyBonusCalculationModel, getClass().getSimpleName());
	}

}