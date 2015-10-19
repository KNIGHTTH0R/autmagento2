package com.tests.uss15.us15003;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.http.ApiCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.MyBusinessSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandlers.CartCalculator;
import com.tools.datahandlers.DataGrabber;
import com.tools.env.constants.ConfigConstants;
import com.tools.env.variables.UrlConstants;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;

@WithTag(name = "US15.3 Check SC kobo subscription and SFM order details in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_3.class)
@RunWith(ThucydidesRunner.class)
public class US15003SubscribedStyleCoachCheckoutProcessTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps customerRegistrationSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public MyBusinessSteps myBusinessSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

	private static String jewelryDiscount;
	private static String marketingDiscount;
	private static String shippingValue;
	private static String taxClass;
	private CustomerFormModel dataModel;
	private String coboCode;

	private ProductDetailedModel genProduct1;

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

		genProduct1 = ApiCalls.createProductModel();
		genProduct1.setPrice("329.90");
		ApiCalls.createApiProduct(genProduct1);

		Properties prop = new Properties();
		InputStream input = null;

		try {

			dataModel = MongoReader.grabCustomerFormModels("US15003StyleCoachRegistrationTest").get(0);
			dataModel.setEmailName(dataModel.getEmailName().replace(ConfigConstants.MAILINATOR, ConfigConstants.EVOZON));

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss15" + File.separator + "us15003.properties");
			prop.load(input);

			jewelryDiscount = prop.getProperty("jewelryDiscount");
			marketingDiscount = prop.getProperty("marketingDiscount");
			shippingValue = prop.getProperty("shippingPrice");
			taxClass = prop.getProperty("taxClass");

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
	public void us15003SubscribedStyleCoachCheckoutProcessTest() {
		customerRegistrationSteps.performLogin(dataModel.getEmailName(), dataModel.getPassword());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		coboCode = myBusinessSteps.getKoboCode();
		homeSteps.clickonGeneralView();
		customerRegistrationSteps.wipeCart();
		BasicProductModel productData;

		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0", ConfigConstants.DISCOUNT_50);
		CartCalculator.productsList50.add(productData);

		CartCalculator.calculateJMDiscounts(jewelryDiscount, marketingDiscount, taxClass, shippingValue);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		cartSteps.goToShipping();

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		if (MongoReader.getContext().contentEquals("de")) {
			paymentSteps.payWithBankTransfer();
		} else {
			paymentSteps.payWithBankTransferEs();
		}

		confirmationSteps.agreeAndCheckout();

		checkoutValidationSteps.verifySuccessMessage();

	}

	@After
	public void saveData() {
		MongoWriter.saveKoboCode(coboCode, getClass().getSimpleName());
		MongoWriter.saveShippingModel(CartCalculator.shippingCalculatedModel, getClass().getSimpleName());
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName());
		for (BasicProductModel product : CartCalculator.allProductsListRecalculated) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName());
		}
	}
}