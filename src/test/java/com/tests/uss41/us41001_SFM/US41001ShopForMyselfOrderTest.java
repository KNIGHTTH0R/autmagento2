package com.tests.uss41.us41001_SFM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.backend.ImportExport.ImportExportSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;
import com.tools.CustomVerification;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.constants.ConfigConstants;
import com.tools.constants.FilePaths;
import com.tools.constants.SoapKeys;
import com.tools.constants.UrlConstants;
import com.tools.data.frontend.BasicProductModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

@WithTag(name = "US3.1 Shop for myself VAT valid and no SMB billing and shipping AT", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class US41001ShopForMyselfOrderTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public ValidationWorkflows validationWorkflows;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public CustomVerification customVerifications;
	@Steps
	public ImportExportSteps importExportSteps;
	

	private CreditCardModel creditCardData = new CreditCardModel();

	private String username, password;
	private static String addressString;

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	public static List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();


		genProduct1 =new ProductDetailedModel();
		genProduct1.setPrice("49.90");
		genProduct1.setIp("42");
		genProduct1.setSku("soft-pearl-necklace");

		

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_41_FOLDER + File.separator + "us41001SFM.properties");
			prop.load(input);

			username = prop.getProperty("username");
			password = prop.getProperty("password");
			addressString = prop.getProperty("addressString");

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
		MongoConnector.cleanCollection(getClass().getSimpleName() + SoapKeys.GRAB);
	}

	@Test
	public void us41001ShopForMyselfOrderTest() {
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickonGeneralView();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();
		BasicProductModel productData;

		productData = addProductsWorkflow.setBasicProductToCart(genProduct1, "2", "0", ConfigConstants.DISCOUNT_25);
		productsList.add(productData);
	

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		cartSteps.grabTotals();
		cartSteps.goToShipping();

		shippingSteps.selectAddress(addressString);
		shippingSteps.setSameAsBilling(true);
		shippingSteps.refresh();
		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		DataGrabber.urlModel.setName("Payment URL");
		DataGrabber.urlModel.setUrl(url);
		DataGrabber.orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		DataGrabber.orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		
		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.agreeAndCheckout();
		importExportSteps.createOrderFile(FormatterUtils.extractOrderIDFromURL(url));
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(DataGrabber.orderModel, getClass().getSimpleName() + SoapKeys.GRAB);
		
		for (BasicProductModel product : productsList) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}
