package com.tests.us3;

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
import com.tools.data.CalcDetailsModel;
import com.tools.data.CalculationModel;
import com.tools.data.UrlModel;
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

@WithTag(name = "US003", type = "frontend")
@Story(Application.StyleCoach.Shopping.class)
@RunWith(ThucydidesRunner.class)
public class US003CartSegmentationWithVatTest extends BaseTest {

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
	public CartCalculation calculationSteps;
	@Steps
	public CheckoutValidationSteps checkoutValidationSteps;
	@Steps
	public ShippingSteps shippingSteps;
	@Steps
	public ConfirmationSteps confirmationSteps;
	@Steps
	public ProfileSteps profileSteps;
	@Steps
	public CartWorkflows cartWorkflows;
	@Steps
	public PaymentSteps paymentSteps;

	private static CartTotalsModel cartTotals = new CartTotalsModel();
	private static CartTotalsModel discountTotals = new CartTotalsModel();
	private static ShippingModel shippingTotals = new ShippingModel();
	private static ShippingModel confirmationTotals = new ShippingModel();
	private static List<ProductBasicModel> cartProductsList = new ArrayList<ProductBasicModel>();

	private static CalcDetailsModel discountCalculationModel;
	private static ShippingModel shippingCalculatedModel = new ShippingModel();
	private static UrlModel urlModel = new UrlModel();

	private CreditCardModel creditCardData = new CreditCardModel();

	// extracted from URL in first test - validated in second test
	private static OrderModel orderModel = new OrderModel();
	private List<CalculationModel> totalsList = new ArrayList<CalculationModel>();
	private String username, password;

	// Test data
	private static String jewelryDisount = "100";
	private static String marketingDisount = "150";
	private static String shippingPrice = "5.04";
	private static String addressString = "sss sss, tttt, 3, 2345 Wien, Österreich";

	@Before
	public void setUp() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(Constants.RESOURCES_PATH + "us3\\us003.properties");
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

		// Clean DB
		MongoConnector.cleanCollection(getClass().getSimpleName());
	}

	@Test
	public void uS003CartSegmentationWithVatTest() {
		frontEndSteps.performLogin(username, password);
		ProductBasicModel productData;

		searchSteps.searchAndSelectProduct("R078SV", "AMÉLIE RING");
		productData = productSteps.setProductAddToCart("1", "17");
		cartProductsList.add(productData);
		
		searchSteps.searchAndSelectProduct("R078SV", "AMÉLIE RING");
		productData = productSteps.setProductAddToCart("1", "17");
		cartProductsList.add(productData);

		searchSteps.searchAndSelectProduct("R025WT", "DAMARIS RING");
		productData = productSteps.setProductAddToCart("1", "16");
		cartProductsList.add(productData);

		searchSteps.searchAndSelectProduct("M101 ", "STYLE BOOK HERBST / WINTER 2014 (270 STK)");
		productData = productSteps.setProductAddToCart("1", "0");
		cartProductsList.add(productData);

		headerSteps.openCartPreview();
		headerSteps.goToCart();

		List<CartProductModel> cartProductsWith50Discount = cartSteps.grabProductsDataWith50PercentDiscount();

		List<CartProductModel> cartProductsWith25Discount = cartSteps.grabProductsDataWith25PercentDiscount();

		List<CartProductModel> cartMarketingMaterialsProducts = cartSteps.grabMarketingMaterialProductsData();

		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith25Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartProductsWith50Discount));
		totalsList.add(CartCalculation.calculateTableProducts(cartMarketingMaterialsProducts));

		CalculationModel totalsCartCalculated = CartCalculation.calculateTotalSum(totalsList);
		PrintUtils.printCalculationModel(totalsCartCalculated);

		List<CartProductModel> cartProducts = cartSteps.grabProductsData();
		cartTotals = cartSteps.grabTotals();

		// APPLY DISCOUNTS
		cartSteps.typeJewerlyBonus(jewelryDisount);
		cartSteps.updateJewerlyBonus();
		cartSteps.typeMarketingBonus(marketingDisount);
		cartSteps.updateMarketingBonus();

		discountCalculationModel = calculationSteps.calculateDiscountTotals(totalsList, jewelryDisount, marketingDisount);
		shippingCalculatedModel = calculationSteps.remove119VAT(discountCalculationModel, shippingPrice);
		List<ProductBasicModel> shippingProductsList = calculationSteps.remove119VAT(cartProductsList);

		discountTotals = cartSteps.grabTotals();

		cartSteps.clickGoToShipping();

		shippingSteps.selectAddress(addressString);
		shippingSteps.setSameAsBilling(true);

		List<CartProductModel> shippingProducts = shippingSteps.grabProductsList();
		System.out.println(" --- shippingProducts ---");
		PrintUtils.printList(shippingProducts);
		System.out.println("X --- shippingProducts ---");

		shippingTotals = shippingSteps.grabSurveyData();

		shippingSteps.clickGoToPaymentMethod();

		String url = shippingSteps.grabUrl();
		urlModel.setName("Payment URL");
		urlModel.setUrl(url);
		orderModel.setTotalPrice(FormatterUtils.extractPriceFromURL(url));
		orderModel.setOrderId(FormatterUtils.extractOrderIDFromURL(url));

		paymentSteps.expandCreditCardForm();
		paymentSteps.fillCreditCardForm(creditCardData);

		List<CartProductModel> confirmationProducts = confirmationSteps.grabProductsList();
		confirmationTotals = confirmationSteps.grabConfirmationTotals();

		// Steps to finalize order
		confirmationSteps.agreeAndCheckout();
		checkoutValidationSteps.verifySuccessMessage();

		// Products List validation
		cartWorkflows.setValidateProductsModels(cartProductsList, cartProducts);
		cartWorkflows.validateProducts("CART PHASE PRODUCTS VALIDATION");

		// Need to validate to discounted item prices
		cartWorkflows.setValidateProductsModels(shippingProductsList, shippingProducts);
		cartWorkflows.validateProducts("SHIPPING PHASE PRODUCTS VALIDATION");

		cartWorkflows.setValidateProductsModels(shippingProductsList, confirmationProducts);
		cartWorkflows.validateProducts("CONFIRMATION PHASE PRODUCTS VALIDATION");

		// Totals validation
		cartWorkflows.setCheckCalculationTotalsModels(cartTotals, totalsCartCalculated);
		cartWorkflows.checkCalculationTotals("CART TOTALS");

		cartWorkflows.setVerifyTotalsDiscount(discountTotals, discountCalculationModel);
		cartWorkflows.verifyTotalsDiscount("DISCOUNT TOTALS");

		cartWorkflows.setVerifyShippingTotals(shippingTotals, shippingCalculatedModel);
		cartWorkflows.verifyShippingTotals("SHIPPING TOTALS");

		cartWorkflows.setVerifyShippingTotals(confirmationTotals, shippingCalculatedModel);
		cartWorkflows.verifyShippingTotals("CONFIRMATION TOTALS");

		// Validate URL
		checkoutValidationSteps.checkTotalAmountFromUrl(orderModel.getTotalPrice(), shippingCalculatedModel.getTotalAmount().replace(".", ""));
	}

	@Test
	public void us003UserProfileOrderId() {
		headerSteps.redirectToProfileHistory();
		List<OrderModel> orderHistory = profileSteps.grabOrderHistory();

		String orderId = orderHistory.get(0).getOrderId();
		String orderPrice = orderHistory.get(0).getTotalPrice();
		profileSteps.verifyOrderId(orderId, orderModel.getOrderId());
		profileSteps.verifyOrderPrice(orderPrice, orderModel.getTotalPrice());
		orderModel = orderHistory.get(0);
	}

	@After
	public void saveData() {
		MongoWriter.saveTotalsModel(cartTotals, getClass().getSimpleName());
		MongoWriter.saveTotalsModel(discountTotals, getClass().getSimpleName());

		// Discount calculations - jewelry and marketing
		MongoWriter.saveCalcDetailsModel(discountCalculationModel, getClass().getSimpleName());

		// values with discount and no TAX VAT - calculated values
		MongoWriter.saveShippingModel(shippingCalculatedModel, getClass().getSimpleName());

//		// Values Grabbed from last screen totals
//		MongoWriter.saveShippingModel(confirmationTotals, getClass().getSimpleName());

		// Order status and details
		MongoWriter.saveOrderModel(orderModel, getClass().getSimpleName());

		// Payment URL with values
		MongoWriter.saveUrlModel(urlModel, getClass().getSimpleName());

		// Products list - with initial values
		for (ProductBasicModel product : cartProductsList) {
			MongoWriter.saveProductBasicModel(product, getClass().getSimpleName());
		}
	}

}
