package com.tests.us1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.connectors.mongo.MongoConnector;
import com.steps.EmailSteps;
import com.steps.frontend.CustomerRegistrationSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ProductSteps;
import com.steps.frontend.ProfileSteps;
import com.steps.frontend.SearchSteps;
import com.steps.frontend.checkout.CartSteps;
import com.steps.frontend.checkout.CheckoutValidationSteps;
import com.steps.frontend.checkout.ConfirmationSteps;
import com.steps.frontend.checkout.PaymentSteps;
import com.steps.frontend.checkout.ShippingSteps;
import com.tests.BaseTest;
import com.tools.Constants;
import com.tools.calculation.CartCalculation;
import com.tools.data.CalculationModel;
import com.tools.data.backend.OrderModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.data.frontend.ShippingModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
import com.tools.utils.FormatterUtils;
import com.tools.utils.PrintUtils;
import com.workflows.frontend.CartWorkflows;

@WithTag(name = "US001", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US001StyleCoachShoppingTest extends BaseTest {

	@Steps
	public CustomerRegistrationSteps frontEndSteps;
	@Steps
	public ProductSteps productSteps;
	@Steps
	public SearchSteps searchSteps;
	@Steps
	public HeaderSteps headerSteps;
	@Steps
	public CartSteps cartSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public PaymentSteps paymentSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public CartWorkflows cartWorkflows;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;

	private static OrderModel orderModel;
	private static CalculationModel totalsCalculated = new CalculationModel();

	private static List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private String username, password;
	
	private CreditCardModel creditCardData = new CreditCardModel();
	private List<CalculationModel> calcList = new ArrayList<CalculationModel>();
	public static CartTotalsModel cartTotals;
	
	//Test data Credit card details
	private static String cardNumber;
	private static String cardName;
	private static String cardMonth;
	private static String cardYear;
	private static String cardCVC;

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us1\\us001.properties");
			prop.load(input);
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			cardNumber = prop.getProperty("cardNumber");
			cardName = prop.getProperty("cardName");
			cardMonth = prop.getProperty("cardMonth");
			cardYear = prop.getProperty("cardYear");
			cardCVC = prop.getProperty("cardCVC");

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

		//TODO edit this to prop file
		creditCardData.setCardNumber(cardNumber);
		creditCardData.setCardName(cardName);
		creditCardData.setMonthExpiration(cardMonth);
		creditCardData.setYearExpiration(cardYear);
		creditCardData.setCvcNumber(cardCVC);

		MongoConnector.cleanCollection(getClass().getSimpleName());
		MongoConnector.cleanCollection(getClass().getSimpleName() + Constants.GRAB);

	}

	@Test
	public void us001StyleCoachShoppingTest() {
		frontEndSteps.performLogin(username, password);

		searchSteps.searchAndSelectProduct("M081", "BANNER MIT LOGO");
		ProductBasicModel productData = productSteps.setProductAddToCart("1", "Blue");
		productsList.add(productData);

		searchSteps.searchAndSelectProduct("M058", "GUTSCHEIN FOLGEPARTY");
		productData = productSteps.setProductAddToCart("1", "0");
		productsList.add(productData);

		searchSteps.searchAndSelectProduct("B040DK", "MAGIC VIOLETTA");
		productData = productSteps.setProductAddToCart("1", "0");
		productsList.add(productData);

		searchSteps.searchAndSelectProduct("R084BK", "GABRIELLE RING");
		productData = productSteps.setProductAddToCart("1", "18");
		productsList.add(productData);
		
//		searchSteps.searchAndSelectProduct("MAGIC VIOLETTA", "MAGIC VIOLETTA");
//		productData = productSteps.setProductAddToCart("1", "0");
//		productsList.add(productData);
//
//		searchSteps.searchAndSelectProduct("Rosemary Ring", "ROSEMARY RING");
//		productData = productSteps.setProductAddToCart("1", "18");
//		productsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		cartTotals = cartSteps.grabTotals();
		PrintUtils.printCartTotals(cartTotals);
		
		List<CartProductModel> cartProducts = cartSteps.grabProductsData();
		
		// Calculate cart products by discount
		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();
		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();
		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();
		calcList.add(CartCalculation.calculateTableProducts(cartProductsWith25Discount));
		calcList.add(CartCalculation.calculateTableProducts(cartProductsWith50Discount));
		calcList.add(CartCalculation.calculateTableProducts(cartMarketingMaterialsProducts));
		totalsCalculated = CartCalculation.calculateTotalSum(calcList);

		cartSteps.clickGoToShipping();

		ShippingModel shippingTotals = shippingSteps.grabSurveyData();
		PrintUtils.printShippingTotals(shippingTotals);

		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
		PrintUtils.printList(shippingProducts);

		shippingSteps.clickGoToPaymentMethod();
		
		String url = shippingSteps.grabUrl();
		orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();

		paymentSteps.fillCreditCardForm(creditCardData);

		List<CartProductModel> confirmationProducts = confirmationSteps.grabProductsList();

		confirmationSteps.agreeAndCheckout();

		checkoutValidationSteps.verifySuccessMessage();

		cartWorkflows.setValidateProductsModels(productsList, cartProducts);
		cartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		cartWorkflows.setValidateProductsModels(productsList, shippingProducts);
		cartWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		cartWorkflows.setValidateProductsModels(productsList, confirmationProducts);
		cartWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		cartWorkflows.setCheckCalculationTotalsModels(cartTotals, totalsCalculated);
		cartWorkflows.checkCalculationTotals("CART TOTALS");
		
		//TODO add calculated Shipping values
		cartWorkflows.setVerifyShippingTotals(shippingTotals, shippingTotals);
		cartWorkflows.verifyShippingTotals("SHIPPING TOTALS");
		
		//TODO add Confirmation values validation

	}

	@After
	public void saveData() {
		MongoWriter.saveOrderModel(orderModel, getClass().getSimpleName() + Constants.GRAB);
		MongoWriter.saveTotalsModel(cartTotals, getClass().getSimpleName() + Constants.GRAB);
		for (ProductBasicModel product : productsList) {
			MongoWriter.saveProductBasicModel(product, getClass().getSimpleName() + Constants.GRAB);
		}
	}
}
