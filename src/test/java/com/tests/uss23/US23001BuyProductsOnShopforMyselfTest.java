package com.tests.uss23;

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

import com.connectors.http.MagentoProductsInfoCalls;
import com.connectors.mongo.MongoConnector;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.FooterSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.steps.frontend.checkout.cart.GeneralCartSteps;
import com.steps.frontend.checkout.cart.styleCoachCart.CartSteps;
import com.tests.BaseTest;
import com.tools.cartcalculations.smf.CartCalculator;
import com.tools.constants.FilePaths;
import com.tools.constants.UrlConstants;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.soap.ProductDetailedModel;
import com.tools.datahandler.DataGrabber;
import com.tools.persistance.MongoReader;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.workflows.frontend.AddProductsWorkflow;

@WithTag(name = "US23.1 Stock Sync", type = "Scenarios")
@Story(Application.StockSync.US23_1.class)
@RunWith(SerenityRunner.class)
public class US23001BuyProductsOnShopforMyselfTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public AddProductsWorkflow addProductsWorkflow;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public HomeSteps homeSteps;
	@Steps
	public GeneralCartSteps generalCartSteps;
	@Steps
	public FooterSteps footerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public PaymentSteps paymentSteps;

	private CreditCardModel creditCardData = new CreditCardModel();

	private String username, password;

	private ProductDetailedModel genProduct1 = new ProductDetailedModel();
	private ProductDetailedModel genProduct2 = new ProductDetailedModel();
	private ProductDetailedModel genProduct3 = new ProductDetailedModel();
	private ProductDetailedModel genProduct4 = new ProductDetailedModel();
	private ProductDetailedModel genProduct5 = new ProductDetailedModel();

	private OrderModel orderModel = new OrderModel();

	@Before
	public void setUp() throws Exception {
		CartCalculator.wipe();
		DataGrabber.wipe();

		
		// this is parent configurable:R065SV
		genProduct1 = MagentoProductsInfoCalls.getProductInfo("1289");
	
		// simple :N093SV
		genProduct2 = MagentoProductsInfoCalls.getProductInfo("1658");
		
		//constant stock product:M014 
		genProduct3 = MagentoProductsInfoCalls.getProductInfo("957");
		
		//simple:N105MC
		genProduct4 = MagentoProductsInfoCalls.getProductInfo("3120");

		// bundle parent: K112GY
		genProduct5 = MagentoProductsInfoCalls.getProductInfo("3906");
		

		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(
					UrlConstants.RESOURCES_PATH + FilePaths.US_23_FOLDER + File.separator + "us23001.properties");
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
		
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void us23001BuyProductsOnShopforMyselfTest() {
		frontEndSteps.performLogin(username, password);
		if (!headerSteps.succesfullLogin()) {
			footerSteps.selectWebsiteFromFooter(MongoReader.getContext());
		}
		headerSteps.selectLanguage(MongoReader.getContext());
		homeSteps.clickonGeneralView();
		headerSteps.openCartPreview();
		headerSteps.goToCart();
		generalCartSteps.clearCart();

		addProductsWorkflow.setProductToCart(genProduct1.getSku(), genProduct1.getName(), "1", "18");
		addProductsWorkflow.setProductToCart(genProduct2.getSku(), genProduct2.getName(), "1", "0");
		addProductsWorkflow.setProductToCart(genProduct3.getSku(), genProduct3.getName(), "1", "0");
		addProductsWorkflow.setProductToCart(genProduct4.getSku(), genProduct4.getName(), "1", "0");
		addProductsWorkflow.setProductToCart(genProduct5.getSku(), genProduct5.getName(), "1", "0");

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		cartSteps.grabTotals();
		cartSteps.goToShipping();

		shippingSteps.goToPaymentMethod();

		String url = shippingSteps.grabUrl();
		orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		confirmationSteps.agreeAndCheckout();
	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(orderModel, getClass().getSimpleName());
	}

}
