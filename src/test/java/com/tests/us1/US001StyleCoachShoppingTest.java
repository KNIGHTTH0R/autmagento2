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
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public EmailSteps emailSteps;
	@Steps
	public ProfileSteps profileSteps;

	private OrderModel orderNumber = new OrderModel();

	private List<ProductBasicModel> productsList = new ArrayList<ProductBasicModel>();
	private String username, password;
	private CreditCardModel creditCardData = new CreditCardModel();
	private List<CalculationModel> calcList = new ArrayList<CalculationModel>();

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

		String previewPrice = headerSteps.openCartPreview();
		headerSteps.goToCart();

		CartTotalsModel cartTotals = cartSteps.grabTotals();
		List<CartProductModel> cartProducts = cartSteps.grabProductsData();

		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();

		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();

		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();

		PrintUtils.printList(cartProducts);

		// CartTotalsModel calculatedTotals =
		// checkoutValidationSteps.calculateCartProducts(cartProducts);
		// checkoutValidationSteps.checkTotalsInCart(cartTotals,
		// calculatedTotals);

		cartSteps.clickGoToShipping();

		// TODO - add billing and shipping address forms
		CartTotalsModel shippingTotals = shippingSteps.grabSurveyData();
		// PrintUtils.printCartTotals(shippingTotals);

		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
		PrintUtils.printList(shippingProducts);

		shippingSteps.clickGoToPaymentMethod();

		paymentSteps.expandCreditCardForm();

		paymentSteps.fillCreditCardForm(creditCardData);

//		AddressModel billingAddress = confirmationSteps.grabBillingData();
//		AddressModel shippingAddress = confirmationSteps.grabSippingData();

		List<CartProductModel> confirmationProducts = confirmationSteps.grabProductsList();

		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();

		System.out.println("CART PHASE PRODUCTS VALIDATION");
		checkoutValidationSteps.validateProducts(productsList, cartProducts);
		System.out.println("SHIPPING PHASE PRODUCTS VALIDATION");
		checkoutValidationSteps.validateProducts(productsList, shippingProducts);
		System.out.println("CONFIRMATION PHASE PRODUCTS VALIDATION");
		checkoutValidationSteps.validateProducts(productsList, confirmationProducts);
		
		CalculationModel calc25 = CartCalculation.calculateTableProducts(cartProductsWith25Discount);
		CalculationModel calc50 = CartCalculation.calculateTableProducts(cartProductsWith50Discount);
		CalculationModel calc00 = CartCalculation.calculateTableProducts(cartMarketingMaterialsProducts);
		calcList.add(calc25);
		calcList.add(calc50);
		calcList.add(calc00);
		
		CalculationModel totalsCalculated = CartCalculation.calculateTotalSum(calcList);
		
		checkoutValidationSteps.checkTotals(totalsCalculated, cartTotals);
		
		
		//After validation - grab order number
		headerSteps.goToProfile();
		profileSteps.openProfileHistory();
		List<OrderModel> orderHistory = profileSteps.grabOrderHistory();
		System.out.println("ORDER ID: " + orderHistory.get(0).getOrderId());
		
		String orderId = orderHistory.get(0).getOrderId();
		orderNumber.setOrderId(orderId);
	}

	
	@After
	public void saveData(){
		MongoWriter.saveOrderModel(orderNumber , getClass().getSimpleName());
	}
}
