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

import com.connectors.http.ApacheHttpHelper;
import com.connectors.http.MagentoProductCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.cartcalculations.regularUser.RegularCartTotalsCalculation;
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.constants.ContextConstants;
import com.tools.constants.EnvironmentConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.frontend.TermPurchaseIpModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;
import com.workflows.frontend.regularUser.RegularCartValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US8.7 Customer Buy With Term Purchase Test", type = "Scenarios")
@Story(Application.RegularCart.US8_7.class)
@RunWith(SerenityRunner.class)
public class US32002RegularOrderAllowedForTPTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public AddRegularProductsWorkflow addRegularProductsWorkflow;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public RegularCartValidationWorkflows regularCartValidationWorkflows;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public FooterSteps footerSteps;

	private String username, password;
	private String discountClass;
	private String billingAddress, shippingAddress;
	private String shippingValue;
	private String voucherCode;
	private String voucherValue;
	private CreditCardModel creditCardData = new CreditCardModel();
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	private TermPurchaseIpModel ipModel = new TermPurchaseIpModel();
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	public static List<ProductDetailedModel> allProductsList;

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();
		DataGrabber.wipe();
		
		allProductsList = new ArrayList<ProductDetailedModel>();

		//immediate
		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setIp("84");
		genProduct1.setPrice("49.90");
		MagentoProductCalls.createApiProduct(genProduct1);

		//immediate with TP
		genProduct2 = MagentoProductCalls.createProductModel();
		genProduct2.getStockData().setAllowedTermPurchase("1");
		genProduct2.setPrice("50.00");
		genProduct2.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct2);
		genProduct2.getStockData().setEarliestAvailability(DateUtils.getCurrentDate("yyyy-MM-dd"));

		//TP
		genProduct3 = MagentoProductCalls.createNotAvailableYetProductModel();
		genProduct3.setPrice("9.90");
		genProduct3.setIp("0");
		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8007.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");

			discountClass = prop.getProperty("discountClass");
			billingAddress = prop.getProperty("billingAddress");
			shippingAddress = prop.getProperty("shippingAddress");
			shippingValue = prop.getProperty("shippingValue");

			voucherCode = prop.getProperty("voucherCode");
			voucherValue = prop.getProperty("voucherValue");

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

//		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP0");
//		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP1");
//		MongoConnector.cleanCollection(getClass().getSimpleName() + "TP2");
//		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us32002RegularOrderAllowedForTPTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.goToNewItems();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();

		addRegularProductsWorkflow.addProductToCart(genProduct1, "1", "0");
		addRegularProductsWorkflow.addProductToCart(genProduct2, "1", "0");
		allProductsList.add(genProduct2);
		addRegularProductsWorkflow.setBasicProductToCart(genProduct3, "1", "0");
		allProductsList.add(genProduct3);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		String mostAwayEarliest = GeneralCartCalculations.sortDates(allProductsList, "yyyy-MM-dd")
				.get(allProductsList.size() - 1).getStockData().getEarliestAvailability();

		for (ProductDetailedModel product : allProductsList) {

			List<String> grabbedDates = regularUserCartSteps.getAllDeliveryDates(product.getSku(),
					new Locale.Builder().setLanguage(MongoReader.getContext()).build());

			List<String> expectedDates = GeneralCartCalculations.calculateDeliveryDates(
					product.getStockData().getEarliestAvailability(), mostAwayEarliest,
					DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 14),
					DateUtils.addDaysToAAGivenDate(DateUtils.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd", 28), 45, 49);

			regularUserCartSteps.validateDeliveryDates(product.getSku(),grabbedDates, expectedDates);
		}
		
	}


		
}