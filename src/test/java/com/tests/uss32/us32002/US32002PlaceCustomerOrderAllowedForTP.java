package com.tests.uss32.us32002;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
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
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.HostBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.HostDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US11.8 Party Host Buys For Customer With 0 Amount Immediate and Tp Products", type = "Scenarios")
@Story(Application.PlaceACustomerOrderCart.US11_8.class)
@RunWith(SerenityRunner.class)
public class US32002PlaceCustomerOrderAllowedForTP extends BaseTest {

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

	public static List<HostBasicProductModel> allProductsList;
	private String username, password, customerName;
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;

	@Before
	public void setUp() throws Exception {
		HostCartCalculator.wipe();
		HostDataGrabber.wipe();
		allProductsList = new ArrayList<HostBasicProductModel>();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.getStockData().setAllowedTermPurchase("1");
		genProduct1.setPrice("50.00");
		genProduct1.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct1);
		genProduct1.getStockData().setEarliestAvailability(DateUtils.getCurrentDate("yyyy-MM-dd"));

		genProduct2 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct2.setPrice("9.90");
		genProduct2.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct2);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss11" + File.separator + "us11008.properties");
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

	}

	@Test
	public void us32002PlaceCustomerOrderAllowedForTP() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		do {
			loungeSteps.clickOrderForCustomer();
			partyDetailsSteps.orderForCustomer();
			partyDetailsSteps.orderForCustomerFromParty(customerName);
		} while (!orderForCustomerCartSteps.getCartOwnerInfo().contains(customerName.toUpperCase()));
		generalCartSteps.clearCart();

		String deliveryTP1 = DateUtils.getFirstFridayAfterDate(genProduct2.getStockData().getEarliestAvailability(),
				"yyyy-MM-dd");

		HostBasicProductModel productData;

		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct1, "1", "0");
		if (!genProduct1.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(deliveryTP1);
		productData.setEarliestAvailability(genProduct1.getStockData().getEarliestAvailability());
		allProductsList.add(productData);

		productData = addProductsForCustomerWorkflow.setHostProductToCart(genProduct2, "1", "0");
		if (!genProduct2.getStockData().getEarliestAvailability().contentEquals(""))
			productData.setDeliveryDate(deliveryTP1);
		productData.setEarliestAvailability(genProduct2.getStockData().getEarliestAvailability());
		allProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		// asta e hardcodata,trebe facut ceva
		String mostAwayEarliest = genProduct2.getStockData().getEarliestAvailability();

		for (HostBasicProductModel product : allProductsList) {

			List<String> grabbedDates = regularUserCartSteps.getAllDeliveryDates(product.getProdCode(),
					new Locale.Builder().setLanguage(MongoReader.getContext()).build());

			List<String> expectedDates = GeneralCartCalculations.calculateDeliveryDates(
					product.getEarliestAvailability(), mostAwayEarliest,
					DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 14),
					DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 28), 45, 49);

			regularUserCartSteps.validateDeliveryDates(grabbedDates, expectedDates);
		}
	}
}
