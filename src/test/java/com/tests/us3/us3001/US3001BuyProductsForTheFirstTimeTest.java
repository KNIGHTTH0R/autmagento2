package com.tests.us3.us3001;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
import com.workflows.frontend.AddProductsWorkflow;
import com.workflows.frontend.ValidationWorkflows;

@WithTag(name = "US3.1 Shop for myself VAT valid and no SMB billing and shipping AT", type = "Scenarios")
@Story(Application.ShopForMyselfCart.US3_1.class)
@RunWith(SerenityRunner.class)
public class US3001BuyProductsForTheFirstTimeTest extends BaseTest {

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

	private CreditCardModel creditCardData = new CreditCardModel();

	private String username, password;
	private static String addressString;

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3 = new ProductDetailedModel();
	public static List<BasicProductModel> productsList = new ArrayList<BasicProductModel>();
	public static List<ProductDetailedModel> createdProductsList = new ArrayList<ProductDetailedModel>();

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

//		genProduct1 = MagentoProductCalls.createProductModel();
//		genProduct1.setIp("84");
//		genProduct1.setPrice("49.90");
//		MagentoProductCalls.createApiProduct(genProduct1);
//
//		genProduct2 = MagentoProductCalls.createProductModel();
//		genProduct2.setIp("25");
//		genProduct2.setPrice("89.00");
//		MagentoProductCalls.createApiProduct(genProduct2);
//
//		genProduct3 = MagentoProductCalls.createMarketingProductModel();
//		genProduct3.setIp("0");
//		genProduct3.setPrice("229.00");
//		MagentoProductCalls.createApiProduct(genProduct3);
		
		createdProductsList = MongoReader.grabProductDetailedModel("CreateProductsTest" + SoapKeys.GRAB);
		
		genProduct1 = createdProductsList.get(0);
		genProduct2 = createdProductsList.get(1);
		genProduct3 = createdProductsList.get(2);
		

//		genProduct1.setName(productsList.get(0).getName());
//		genProduct1.setSku(productsList.get(0).getProdCode());
//		genProduct1.setIp("84");
//		genProduct1.setPrice("49.90");
//		genProduct2.setName(productsList.get(1).getName());
//		genProduct2.setSku(productsList.get(1).getProdCode());
//		genProduct2.setIp("25");
//		genProduct2.setPrice("89.00");
//		genProduct3.setName(productsList.get(2).getName());
//		genProduct3.setSku(productsList.get(2).getProdCode());
//		genProduct3.setIp("0");
//		genProduct3.setPrice("229.00");


		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(UrlConstants.RESOURCES_PATH + FilePaths.US_03_FOLDER + File.separator + "us3001.properties");
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
	public void us3001BuyProductsForTheFirstTimeTest() {
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
		productData = addProductsWorkflow.setBasicProductToCart(genProduct2, "1", "0", ConfigConstants.DISCOUNT_25);
		productsList.add(productData);
		productData = addProductsWorkflow.setBasicProductToCart(genProduct3, "1", "0", ConfigConstants.DISCOUNT_0);
		productsList.add(productData);
	

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		cartSteps.grabTotals();
		cartSteps.goToShipping();
		
		shippingSteps.selectAddress(addressString);
		shippingSteps.setSameAsBilling(true);
		shippingSteps.refresh();
		shippingSteps.goToPaymentMethod();

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.agreeAndCheckout();
	}

	@After
	public void saveData() {
		for (BasicProductModel product : productsList) {
			MongoWriter.saveBasicProductModel(product, getClass().getSimpleName() + SoapKeys.GRAB);
		}
	}

}
