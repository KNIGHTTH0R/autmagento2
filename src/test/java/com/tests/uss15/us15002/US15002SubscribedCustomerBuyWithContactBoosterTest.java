package com.tests.uss15.us15002;

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
import com.tools.cartcalculations.regularUser.RegularUserCartCalculator;
import com.tools.constants.ConfigConstants;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.CustomerFormModel;
import com.tools.data.frontend.RegularBasicProductModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.RegularUserDataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.regularUser.AddRegularProductsWorkflow;
import com.workflows.frontend.regularUser.RegularCartValidationWorkflows;

@WithTag(name = "US15.2 Check registered user with kobo all states in mailchimp ", type = "Scenarios")
@Story(Application.Newsletter.US15_2.class)
@RunWith(SerenityRunner.class)
public class US15002SubscribedCustomerBuyWithContactBoosterTest extends BaseTest {

	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ShippingPartySectionSteps shippingPartySectionSteps;
	@Steps
	public RegularUserCartSteps regularUserCartSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
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

	private String koboCode;
	private String shippingValue;
	private String discountClass;
	private String voucherValue;
	private CustomerFormModel dataModel;
	private ProductDetailedModel genProduct1;
	private ProductDetailedModel genProduct2;
	private ProductDetailedModel genProduct3;

	@Before
	public void setUp() throws Exception {
		RegularUserCartCalculator.wipe();
		RegularUserDataGrabber.wipe();

		genProduct1 = MagentoProductCalls.createProductModel();
		genProduct1.setPrice("89.00");
		MagentoProductCalls.createApiProduct(genProduct1);

		genProduct2 = MagentoProductCalls.createPomProductModel();
		genProduct2.setPrice("49.90");
		voucherValue = genProduct2.getPrice();
		MagentoProductCalls.createApiProduct(genProduct2);

		genProduct3 = MagentoProductCalls.createProductModel();
		genProduct3.setPrice("10.00");
		MagentoProductCalls.createApiProduct(genProduct3);

		Properties prop = new Properties();
		InputStream input = null;

		dataModel = MongoReader.grabCustomerFormModels("US15002KoboRegistrationNewsletterSubscribeTest").get(0);
		dataModel.setEmailName(dataModel.getEmailName().replace(ConfigConstants.WEB_MAIL, ConfigConstants.EVOZON));

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + "uss15" + File.separator + "us15002.properties");
			prop.load(input);

			discountClass = prop.getProperty("discountClass");
			shippingValue = prop.getProperty("shippingValue");
			
			input = new FileInputStream(UrlConstants.RESOURCES_PATH_COMMON + "koboVouchers.properties");
			prop.load(input);
			koboCode = prop.getProperty("koboCodemihaialexandrubarta@gmail.com");

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
	public void us15002SubscribedCustomerBuyWithContactBoosterTest() {
		customerRegistrationSteps.performLogin(dataModel.getEmailName(), dataModel.getPassword());
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		headerSteps.goToProfile();
		homeSteps.goToNewItems();
		customerRegistrationSteps.wipeRegularCart();
		RegularBasicProductModel productData;

		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct1, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0");
		RegularUserCartCalculator.allProductsList.add(productData);
		productData = addRegularProductsWorkflow.setBasicProductToCart(genProduct3, "4", "0");
		RegularUserCartCalculator.allProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		regularUserCartSteps.typeCouponCode(koboCode);

		RegularUserCartCalculator.calculateCartAndShippingTotals(RegularUserCartCalculator.allProductsList, discountClass, shippingValue, voucherValue);

		regularUserCartSteps.clickGoToShipping();
		shippingPartySectionSteps.clickPartyNoOption();

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		RegularUserDataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		RegularUserDataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		if (MongoReader.getContext().contentEquals("de")) {
			paymentSteps.payWithBankTransfer();
		} else {
			paymentSteps.payWithBankTransferEs();
		}

		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();

		customVerifications.printErrors();
	}

	@After
	public void saveData() {
		MongoWriter.saveShippingModel(RegularUserCartCalculator.shippingCalculatedModel, getClass().getSimpleName());
		MongoWriter.saveOrderModel(RegularUserDataGrabber.orderModel, getClass().getSimpleName());
		for (RegularBasicProductModel product : RegularUserCartCalculator.allProductsList) {
			MongoWriter.saveRegularBasicProductModel(product, getClass().getSimpleName());
		}
	}
}
