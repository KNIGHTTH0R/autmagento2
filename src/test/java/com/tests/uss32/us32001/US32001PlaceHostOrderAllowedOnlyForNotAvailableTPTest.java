package com.tests.uss32.us32001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.LoungeSteps;
import com.steps.frontend.PartyDetailsSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.partyHost.OrderForCustomerCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.cartcalculations.partyHost.HostCartCalculator;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.UrlModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.HostDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US11.8 Party Host Buys For Customer With 0 Amount Immediate and Tp Products", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_8.class)
@RunWith(SerenityRunner.class)
public class US32001PlaceHostOrderAllowedOnlyForNotAvailableTPTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public PartyDetailsSteps partyDetailsSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public OrderForCustomerCartSteps orderForCustomerCartSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddProductsForCustomerWorkflow addProductsForCustomerWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public LoungeSteps loungeSteps;

	public static List<ProductDetailedModel> allProductsList;
	private String username, password;
	private ProductDetailedModel genProduct1, genProduct2, genProduct3;
	private static UrlModel partyUrlModel = new UrlModel();
	List<List<String>> dropdownDatesList = new ArrayList<List<String>>();

	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();
		allProductsList = new ArrayList<ProductDetailedModel>();
		
		// immediate
		genProduct1 = MagentoProductCalls.createProductModel();
		MagentoProductCalls.createApiProduct(genProduct1);
		genProduct1.getStockData().setEarliestAvailability(DateUtils.getCurrentDate("yyyy-MM-dd"));

		// immediate with TP
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.getStockData().setAllowedTermPurchase("1");
		MagentoProductCalls.createApiProduct(genProduct2);
		genProduct2.getStockData().setEarliestAvailability(DateUtils.getCurrentDate("yyyy-MM-dd"));

		// TP
		genProduct3 = MagentoProductCalls.createNotAvailableYetProductModel();
		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss11" + File.separator + "us11008.properties");
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
		partyUrlModel = MongoReader.grabUrlModels("US32001CreatePartyWithStylistHostTest" + SoapKeys.GRAB).get(0);
		System.out.println("partyUrlModel " + partyUrlModel.getUrl());
	}

	@Test
	public void us32001PlaceHostOrderAllowedOnlyForNotAvailableTPTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.navigateToPartyPageAndStartOrder(partyUrlModel.getUrl());
		generalCartSteps.clearCart();


		addProductsForCustomerWorkflow.addProductToCart(genProduct2, "1", "0");
		// allProductsList.add(genProduct2);
		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.verifyThatTermPurchaseIsNotAvailable(genProduct2.getSku());
		regularUserCartSteps.verifyThatTermPurchasePaymentAndShippingBlockIsNotAvailable();

		addProductsForCustomerWorkflow.addProductToCart(genProduct3, "1", "0");
		allProductsList.add(genProduct3);
		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.verifyDeliverAllImediatlyIsDisabled();
		regularUserCartSteps.verifyMultipleDeliveryOptionIsEnabled();
		regularUserCartSteps.verifyThatMultipleDeliveryOptionIsChecked();
		regularUserCartSteps.verifyDeliverAllOnThisDateIsDisabled();
		// click on DeliverALLonThisDates

		String mostAwayEarliest = GeneralCartCalculations.sortDates(allProductsList, "yyyy-MM-dd")
				.get(allProductsList.size() - 1).getStockData().getEarliestAvailability();

		for (ProductDetailedModel product : allProductsList) {

			List<String> grabbedDates = regularUserCartSteps.getAllDeliveryDates(product.getSku(),
					new Locale.Builder().setLanguage(MongoReader.getContext()).build());

			List<String> expectedDates = GeneralCartCalculations.calculateDeliveryDates(
					product.getStockData().getEarliestAvailability(), mostAwayEarliest,
					DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 14),
					DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 28), 45, 49);
			dropdownDatesList.add(expectedDates);

			regularUserCartSteps.validateDeliveryDates(product.getSku(), grabbedDates, expectedDates);

		}

		addProductsForCustomerWorkflow.addProductToCart(genProduct1, "1", "0");
		allProductsList.add(genProduct1);
		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.verifyDeliverAllImediatlyIsDisabled();
		regularUserCartSteps.verifyMultipleDeliveryOptionIsEnabled();
		regularUserCartSteps.verifyThatMultipleDeliveryOptionIsChecked();
		regularUserCartSteps.verifyDeliverAllOnThisDateIsDisabled();
		regularUserCartSteps.verifyThatTermPurchaseIsNotAvailable(genProduct1.getSku());

		// verify if TP block is not displayed on product side

		
	}
}
