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

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.MagentoProductCalls;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.regularCart.RegularUserCartSteps;
import com.steps.frontend.checkout.shipping.regularUser.ShippingPartySectionSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.GeneralCartCalculations;
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.constants.UrlConstants;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.requirements.Application;
import com.tools.utils.DateUtils;
import com.workflows.frontend.partyHost.AddProductsForCustomerWorkflow;

@WithTag(name = "US33.2 Regular CustomerOrder Allowed For TP", type = "Scenarios")
@Story(Application.RegularCart.US8_7.class)
@RunWith(SerenityRunner.class)
public class US32001RegularOrderAllowedOnlyForNotAvailableTPTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public AddProductsForCustomerWorkflow addProductsForCustomerWorkflow;

	private String username, password;
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;
	public static List<ProductDetailedModel> allProductsList;
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();
	List<List<String>> dropdownDatesList = new ArrayList<List<String>>();

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();
		DataGrabber.wipe();

		// createdProductsList =
		// MongoReader.grabProductDetailedModel("CreateProductsTest" +
		// SoapKeys.GRAB);
		// genProduct1 = createdProductsList.get(1);
		// genProduct2 = createdProductsList.get(8);
		// genProduct3 = createdProductsList.get(9);

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

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "us8" + File.separator + "us8007.properties");
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
	public void us32001RegularOrderAllowedOnlyForNotAvailableTPTest() throws ParseException {
		customerRegistrationSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.goToNewItems();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();

		addProductsForCustomerWorkflow.addProductToCart(genProduct3, "1", "0");
		allProductsList.add(genProduct3);
		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.verifyDeliverAllImediatlyIsDisabled();
		regularUserCartSteps.verifyMultipleDeliveryOptionIsEnabled();
		regularUserCartSteps.verifyDeliverAllOnThisDateIsChecked();
		regularUserCartSteps.verifyDeliverAllOnThisDateIsEnabled();
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
		

		List<String> expectedDeliverAllAtOnceDates = GeneralCartCalculations.getCommonDates(dropdownDatesList);
		List<String> grabedDeliverAllAtOnceDates = regularUserCartSteps
				.grabbDeliverAllAtOnceDates(new Locale.Builder().setLanguage(MongoReader.getContext()).build());
		regularUserCartSteps.validateDeliverAllAtOnceDates(expectedDeliverAllAtOnceDates, grabedDeliverAllAtOnceDates);
		
		addProductsForCustomerWorkflow.addProductToCart(genProduct2, "1", "0");
		// allProductsList.add(genProduct2);
		headerSteps.openCartPreview();
		headerSteps.goToCart();


		regularUserCartSteps.verifyDeliverAllImediatlyIsDisabled();
		regularUserCartSteps.verifyMultipleDeliveryOptionIsEnabled();
		regularUserCartSteps.verifyThatMultipleDeliveryOptionIsChecked();
		regularUserCartSteps.verifyDeliverAllOnThisDateIsDisabled();
		regularUserCartSteps.verifyThatTermPurchaseIsNotAvailable(genProduct2.getSku());

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