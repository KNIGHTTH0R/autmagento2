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
import com.tools.PrintUtils;
import com.tools.calculation.CartCalculation;
import com.tools.data.CalculationModel;
import com.tools.data.OrderModel;
import com.tools.data.frontend.CartProductModel;
import com.tools.data.frontend.CartTotalsModel;
import com.tools.data.frontend.CreditCardModel;
import com.tools.data.frontend.ProductBasicModel;
import com.tools.persistance.MongoWriter;
import com.tools.requirements.Application;
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

	private static OrderModel orderNumber = new OrderModel();

	private List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private String username, password;
	private CreditCardModel creditCardData = new CreditCardModel();
	private List<CalculationModel> calcList = new ArrayList<CalculationModel>();
	public static CartTotalsModel cartTotals;

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us1\\us001.properties");
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

		creditCardData.setCardNumber("4111 1111 1111 1111");
		creditCardData.setCardName("test");
		creditCardData.setMonthExpiration("06");
		creditCardData.setYearExpiration("2016");
		creditCardData.setCvcNumber("737");

		MongoConnector.cleanCollection(getClass().getSimpleName());

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

		searchSteps.searchAndSelectProduct("MAGIC VIOLETTA", "MAGIC VIOLETTA");
		productData = productSteps.setProductAddToCart("1", "0");
		productsList.add(productData);

		searchSteps.searchAndSelectProduct("Rosemary Ring", "ROSEMARY RING");
		productData = productSteps.setProductAddToCart("1", "18");
		productsList.add(productData);

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
		CalculationModel totalsCalculated = CartCalculation.calculateTotalSum(calcList);

		cartSteps.clickGoToShipping();

		// TODO - validate shipping without IP points
		CartTotalsModel shippingTotals = shippingSteps.grabSurveyData();
		PrintUtils.printCartTotals(shippingTotals);

		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
		PrintUtils.printList(shippingProducts);

		shippingSteps.clickGoToPaymentMethod();

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

		cartWorkflows.setCheckCalculationTotalsModels(totalsCalculated, cartTotals);
		cartWorkflows.checkCalculationTotals("CART TOTALS");

		// Shipping has no IP
		// checkoutValidationSteps.checkCalculationTotals("SHIPPING TOTALS",
		// totalsCalculated, shippingTotals);
	}

	@Test
	public void us001UserProfileOrderId() {

		// After validation - grab order number
		headerSteps.redirectToProfileHistory();
		List<OrderModel> orderHistory = profileSteps.grabOrderHistory();

		String orderId = orderHistory.get(0).getOrderId();
		orderNumber.setOrderId(orderId);
		profileSteps.verifyOrderId(orderId);
	}

	@After
	public void saveData() {
		
		MongoWriter.saveOrderModel(orderNumber, getClass().getSimpleName());
		MongoWriter.saveTotalsModel(cartTotals, getClass().getSimpleName());
	}
}
